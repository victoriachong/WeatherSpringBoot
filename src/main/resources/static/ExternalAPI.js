
 function storeSearchCity(){
    let search = document.getElementById("citySearch")
    console.log(search.value)
    sessionStorage.setItem("search_location", search.value)
     window.location.reload(true)
}

 async function callCity(){
     var events = []
     var conditioncolours = {"Clear":"#2191E6", "Partially cloudy":"#4C85B0", "Rain":"#2F6A97", "Rain, Partially cloudy":"#5C88AB", "Overcast":"#577C98", "Rain, Overcast":"#466373"}
     var searchInput = sessionStorage.getItem("search_location")
     if (searchInput!=null){
         var url = URIstring(searchInput)
         console.log(url)
         var jsonWeatherData = await getWeather(url)
         var location = jsonWeatherData.resolvedAddress
         var tzoffset = jsonWeatherData.tzoffset*60*60
         console.log(jsonWeatherData)

         jsonWeatherData.days.forEach(day => {
             var daydescription= `description: ${day.description} <br> temperature: ${day.temp} <br> feels like: ${day.feelslike} <br> humidity: ${day.humidity} <br> probability of precipitation: ${day.precipprob} <br> sunrise: ${day.sunrise}<br> sunset: ${day.sunset}`
             var dayEvent = {
                 title : day.conditions,
                 start: new Date((day.datetimeEpoch+tzoffset)*1000),
                 end: new Date((day.datetimeEpoch+tzoffset+28800)*1000),
                 description: daydescription,
                 color: conditioncolours[day.conditions],
                 allDay: true
             }
             events.push(dayEvent)

             day.hours.forEach(hour=>{
                 var hourdescription= `temperature: ${hour.temp} <br> feels like: ${hour.feelslike} <br> humidity: ${hour.humidity} <br> probability of precipitation: ${hour.precipprob}`
                 var hourEvent = {
                     title : hour.conditions,
                     start: new Date((hour.datetimeEpoch+tzoffset)*1000),
                     end: new Date((hour.datetimeEpoch+tzoffset+3600)*1000),
                     description: hourdescription,
                     color: conditioncolours[hour.conditions]
                 }
                 // console.log(hourEvent)
                 events.push(hourEvent)
             })
         })

         // var day0 = jsonWeatherData.days[0].hours[0]

         return events
     }
     return null
 }

function URIstring(city){
    var urlstring = `https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${city}/next14days`
    const uri = new URL(urlstring)
    uri.searchParams.append("key","TQRSXJ6KHCMYLJ63MNU6EEHWV" )
    uri.searchParams.append("unitGroup","metric" )
    uri.searchParams.append("include","hours")
    return uri.href
}

async function getWeather(url) {
    return await fetch(url, {method: "GET"})
        .then(resp => resp.json())
        .then(function(data){
            return data
        })
}
