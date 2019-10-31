let latlng;
let marker;
let sessionValue = document.getElementById('sid').value;
let mymap;
let temp = {
    "lat": 0,
    "lng": 0
};
let sid = document.getElementById('sid').value;
let body = document.getElementsByClassName('cards');
let existingElements = {};
let petMarkers = [];
let container = document.getElementById('cards');


let init = () => {
    createMap();
    getLoc();
    fetchPet();
    setInterval(() => {
        getLoc()
    }, 3000)
}

init();

function getElementLocation(loca) {
    let array = loca.split(" ");
    let object = {
        "lat": parseFloat(array[0]),
        "lng": parseFloat(array[1])
    }
    return object;
}

function removeCard(element) {
    document.getElementById(element).remove();
    mymap.removeLayer(petMarkers[element]);

}

function createCard(element) {
    let div = document.createElement('div');
    div.className = 'card';
    div.setAttribute('id', element.id);
    loc = getElementLocation(element.location);
    petMarkers[element.id] = L.circle({ 'lat': loc["lat"], 'lng': loc["lng"] }, {
        color: 'red',
        fillColor: '#f03',
        fillOpacity: 0.5,
        radius: 400
    }).addTo(mymap);
    div.innerHTML = "<div class='primary' ><a href='./pet#" + element.id + "'><img class='img-primary' src='./public/img/pets/" + element.gallery + "' alt=''><div class='user-things'> <p>" + element.name + "</p> </a></div>";
    container.appendChild(div)
}

async function fetchPet() {
    // fetch
    let query = await (fetch('./get/loc/' + latlng.lat + '/' + latlng.lng, {
        method: 'GET'
    }));
    // check fetch
    let result = await query.json();
    if (result) {
        // daca nu exista animale le luam pe toate din jur
        if (container.children.length == 0) {
            result.forEach(element => {
                createCard(element);
                // fetchElementId.push(element.id);
                existingElements[element.id] = true;
            });
        }
        // altfel adaugam doar animalele noi aparute
        else {

            for (let i = 0; i < container.children.length; i++) {
                if (!existingElements[container.children[i].id]) {
                    existingElements[container.children[i].id] = true;
                }
                let found = result.find((element) => {
                    return container.children[i].id == element.id;
                });
                if (!found) {
                    delete(existingElements[container.children[i].id]);
                    removeCard(container.children[i].id);
                }
            }
            result.forEach(element => {
                if (!existingElements[element.id]) {
                    createCard(element);
                }
            });
        }

    }
}

function createMap() {

    // initializare mapa
    mymap = L.map('mapid');
    getLoc();
    // mymap.dragging.disable();

    // adresa + token
    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://mapbox.com">Mapbox</a>',
        maxZoom: 30,
        id: 'mapbox.streets',
        accessToken: 'pk.eyJ1IjoiYmJyb29rMTU0IiwiYSI6ImNpcXN3dnJrdDAwMGNmd250bjhvZXpnbWsifQ.Nf9Zkfchos577IanoKMoYQ'
    }).addTo(mymap);

    mymap.setView([0, 0], 13);
    newMarkerGroup = new L.LayerGroup();
}

function isEquivalent(temp, latlong) {

    let aProps = Object.getOwnPropertyNames(temp);
    let bProps = Object.getOwnPropertyNames(latlong);
    if (aProps.length != bProps.length) {
        return false;
    }
    for (let i = 0; i < aProps.length; i++) {
        let propName = aProps[i];
        if (temp[propName] !== latlong[propName]) {
            return false;
        }
    }
    return true;
}

// iau locatia actuala a utilizatorului
function getLoc() {
    navigator.geolocation.getCurrentPosition(function(location) {
        temp = latlng;
        latlng = new L.LatLng(location.coords.latitude, location.coords.longitude);
        mymap.setView(latlng);
        if (!marker) {
            marker = L.marker(latlng).addTo(mymap);
        } else {
            mymap.removeLayer(marker);
            marker = L.marker(latlng).addTo(mymap);
        }
        if (isEquivalent(temp, latlng)) {
            fetchPet();
        }
    });
}


//
// // nu trebuie sa tin locatia in baza de date, o am pe front mereu si fac interogarile din api cu coordonatele latlng
// async function insertLocation(lat, long) {
//     if (sessionValue != '') {
//         let x = await (fetch('./add/' + sessionValue + '/coord/' + lat + '/' + long, { method: "POST" }));
//         console.log(await x);
//     }
// }
//