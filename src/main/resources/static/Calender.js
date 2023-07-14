

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    const calendar = new FullCalendar.Calendar(calendarEl, {
        selectable: true,
        timeZone: 'Europe/Berlin',
        themeSystem: 'bootstrap5',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        eventSources: [
            {events: function(infofetch, success){
                if (sessionStorage.getItem('userid')!=null){
                     fetch('/events/'+sessionStorage.getItem('userid'), {method: "GET"})
                        .then(resp => resp.json())
                        .then(function (data){
                            console.log(data)
                            var events = [];
                            data.forEach(e=>{
                                // console.log(new Date(e.startTime*1000))
                                events.push(
                                    {id: e.id,
                                    title: e.title,
                                    start: new Date(e.startTime*1000),
                                    end: new Date(e.endTime*1000),
                                    description: e.description,
                                    backgroundColor: e.backgroundColor}
                                );
                            })
                            console.log(events)
                            success(events);
                        })
                        .catch(function (error) {
                            console.error(error);
                        })}
                }, color:"yellow"
            },
            {events: async function(infofetch, success){
                var events = await callCity()
                    success(events)
                }, display: 'background'
            }
        ],
        dayMaxEventRows: true, // for all non-TimeGrid views
        views: {
            timeGrid: {
                dayMaxEventRows: 6 // adjust to 6 only for timeGridWeek/timeGridDay
            }
        },

        select: function(info){
            createEventsTrigger(info)
        },
        eventDidMount: function(info) {
            const exampleEl = info.el
            const popover = new bootstrap.Popover(exampleEl, {
                trigger:'hover',
                html: true,
                title: info.event.title,
                content: info.event.extendedProps.description,
                container: 'body'
            })

        },
        eventClick: function(info){
            editEventsTrigger(info)
        }
    });
    calendar.render();
});

// function getEventsfromDB(){
//     fetch('/events', {method: "GET"})
//         .then(resp => resp.json())
//         .then(function (data) {
//             return data
//         })
//         .catch(function (error) {
//             console.error(error);
//         });
//
// }

function createEventsTrigger(info){
    console.log('selected ' + info.start.toISOString() + ' to ' + info.end.toISOString());
    // console.log(info.startStr.substring(0,info.startStr.length-4))
    const start_datetime = document.getElementById('start-datetime');
    start_datetime.value = info.start.toISOString().substring(0,info.start.toISOString().length-8)
    const end_datetime = document.getElementById('end-datetime');
    end_datetime.value = info.end.toISOString().substring(0,info.end.toISOString().length-8)

    const eventcreator = document.getElementById('event-creator')
    let myModal = new bootstrap.Modal(eventcreator, {})

    myModal.show();
}

function postEvent(){

    var data = {
        "title": document.getElementById('Event-title').value,
        "description": document.getElementById('description-text').value,
        "startTime": Math.floor(new Date(document.getElementById('start-datetime').value+":00.000Z").getTime()/1000),
        "endTime": Math.floor(new Date(document.getElementById('end-datetime').value+":00.000Z").getTime()/1000),
        "userid": sessionStorage.getItem('userid')
    }


    fetch('/events', {method: "POST", headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }, body: JSON.stringify(data)})
        .then(function (resp) {
            console.log(resp)

        })
        .catch(function (error) {
            console.error(error);
        });

    // getEventsfromDB()
    const eventcreator = document.getElementById('event-creator')
    let myModal = new bootstrap.Modal(eventcreator, {})

    document.getElementById('Event-title').value = ""
    document.getElementById('description-text').value = ""
    myModal.hide();

    location.reload()
}

function editEventsTrigger(info){
    console.log('title: ' + info.event.title + ' description: ' + info.event.description);
    var event = info.event
    console.log(event)
    // // console.log(info.startStr.substring(0,info.startStr.length-4))
    document.getElementById('event-title-to-edit').value = event.title
    document.getElementById('description-text-to-edit').value = event.extendedProps.description
    const start_datetime = document.getElementById('start-datetime-to-edit');
    start_datetime.value = event.start.toISOString().substring(0,event.start.toISOString().length-8)
    const end_datetime = document.getElementById('end-datetime-to-edit');
    end_datetime.value = event.end.toISOString().substring(0,event.end.toISOString().length-8)

    const eventeditor = document.getElementById('event-editor')
    let myModal = new bootstrap.Modal(eventeditor, {})
    eventeditor.value = event
    myModal.show();
}

function patchEvent(){
    const eventeditor = document.getElementById('event-editor')
    event = eventeditor.value

    console.log(event.id)

    var data = {
        "title": document.getElementById('event-title-to-edit').value,
        "description": document.getElementById('description-text-to-edit').value,
        "startTime": Math.floor(new Date(document.getElementById('start-datetime-to-edit').value+":00.000Z").getTime()/1000),
        "endTime": Math.floor(new Date(document.getElementById('end-datetime-to-edit').value+":00.000Z").getTime()/1000),
        "userid": 1
    }


    fetch('/events/'+event.id, {method: "PATCH", headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }, body: JSON.stringify(data)})
        .then(function (resp) {
            console.log(resp)
        })
        .catch(function (error) {
            console.error(error);
        });

    // getEventsfromDB()
    const eventcreator = document.getElementById('event-creator')
    let myModal = new bootstrap.Modal(eventcreator, {})

    document.getElementById('Event-title').value = ""
    document.getElementById('description-text').value = ""
    myModal.hide();

    location.reload()
}

function deleteEvent(){
    const eventeditor = document.getElementById('event-editor')
    event = eventeditor.value

    fetch('/events/'+event.id, {method: "DELETE"})
        .then(function (resp) {
            console.log(resp)
        })
        .catch(function (error) {
            console.error(error);
        });

    location.reload()
}
