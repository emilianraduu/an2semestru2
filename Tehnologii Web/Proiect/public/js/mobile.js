let menuBtn = document.getElementById('mobile');
let rmBtn = document.getElementById('rmBtn');
let menu = document.querySelector('.links');
let notifs = document.getElementById('notifs');
let caughtNotifs = [];
let init1 = () => {
    createMap();
    fetchNotif();
    setInterval(() => {
        fetchNotif()
    }, 3000);
}
let notifications = document.getElementById('drop-content');


init1();
menuBtn.addEventListener('click', () => {
    menu.classList.add('show');
});
rmBtn.addEventListener('click', () => {
    menu.classList.remove('show');
});

function show_hide() {
    let click = document.getElementById("drop-content");
    if (click.style.display === "none") {
        click.style.display = "block";
    } else {
        click.style.display = "none";
    }
}


function createMap() {

    // initializare mapa
    mymap = L.map('mapid_hide');

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

async function fetchNotif() {
    // fetch

    let query = await (fetch('./get/notifications/' + sessionValue, {
        method: 'GET'
    }));
    let results = await query.json();
    let bullet = document.createElement('div');
    results.map((result) => {

        bullet.innerHTML = "<div class='bulina'/>";

        if(!caughtNotifs.includes(result['id_pet'])){
            notifs.appendChild(bullet);
            caughtNotifs.push(result['id_pet']);
            notifications.innerHTML = notifications.innerHTML + "<form action='./control/notification-controller.php' enctype='multipart/form-data' method='post' class='dropdown-item'><input name='id_user' value='"+result.id_user +"' hidden/><input name='id_pet' value='" + result.id_pet + "'  hidden /><input type='submit' value='" + result["name"] + " a fost vazut!'/></form>";
        }
        
    })
    

}
