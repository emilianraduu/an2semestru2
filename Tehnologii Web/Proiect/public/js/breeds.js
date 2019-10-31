let breed = document.getElementById('breeds');
let dog = document.getElementById('dog');
let species = document.getElementById('species');
species.addEventListener("change", change);
let latlng;
let marker;
let circle;
let onlyone = true;
let petlocation = document.getElementById("location");

let init = () => {
    createMap();
}
init();


function getLoc() {
    navigator.geolocation.getCurrentPosition(function(location) {
        temp = latlng;
        latlng = new L.LatLng(location.coords.latitude, location.coords.longitude);
        if (!marker) {
            // marker = L.marker(latlng).addTo(mymap);
            circle = L.circle(latlng, {
                color: 'red',
                fillColor: '#f03',
                fillOpacity: 0.5,
                radius: 400
            }).addTo(mymap);
            mymap.setView(latlng);
        } else {
            mymap.removeLayer(marker);
            // marker = L.marker(latlng).addTo(mymap);
        }
    });
}

function createMap() {
    // initializare mapa
    mymap = L.map('mapid');
    getLoc();

    // adresa + token
    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://mapbox.com">Mapbox</a>',
        maxZoom: 30,
        id: 'mapbox.streets',
        accessToken: 'pk.eyJ1IjoiYmJyb29rMTU0IiwiYSI6ImNpcXN3dnJrdDAwMGNmd250bjhvZXpnbWsifQ.Nf9Zkfchos577IanoKMoYQ'
    }).addTo(mymap);

    mymap.on('click', function(e) {
        if (!marker) {
            marker = new L.marker(e.latlng).addTo(mymap);
            petlocation.value = e.latlng['lat'] + ' ' + e.latlng['lng'];
        } else {
            mymap.removeLayer(marker);
            marker = new L.marker(e.latlng).addTo(mymap);
            petlocation.value = e.latlng['lat'] + ' ' + e.latlng['lng'];
        }
        console.log(petlocation.value);
    });

    mymap.setView([0, 0], 13);

    newMarkerGroup = new L.LayerGroup();
    // mymap.dragging.disable();
}


function change() {
    if (species.value == "Dog") {
        deleteOptions();
        getDogBreeds();
    }
    if (species.value == "Cat") {
        deleteOptions();
        getCatBreeds();
    }
}

function deleteOptions() {
    let child = breed.lastElementChild;
    while (child) {
        breed.removeChild(child);
        child = breed.lastElementChild;
    }
}

async function getDogBreeds() {
    let breeds = await (fetch('https://dog.ceo/api/breeds/list/all'));
    let response = await (breeds.json());

    Object.keys(response.message).forEach(function(value, i) {
        let option = document.createElement('option');
        option.text = value;
        breed.append(option);
    });
}

async function getCatBreeds() {
    let breeds = await (fetch('https://api.thecatapi.com/v1/breeds', {
        headers: {
            'x-api-key': "3aab24bf-1e6a-45c1-bc7f-24ec43299289"
        }
    }));
    let response = await breeds.json();
    response.forEach(element => {
        let option = document.createElement('option');
        option.text = element.name;
        breed.append(option);
    })
}