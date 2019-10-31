# Lost Pets Architecture

Proiect la materia Tehnologii Web ce consta intr-o aplicatie web menita pentru persoanele care si-au pierdut animalul de companie si pentru personale dornice sa ajute in gasirea acestora.

## Enunt proiect

Creați o aplicație Web care să vină în ajutorul recuperării animalelor pierdute de o persoana. Proprietarul animalului pierdut va completa un formular cu detalii despre acesta: nume, fotografie, zona în care a fost ultima dată văzut, plus alte detalii de interes (specie, existenta unor semnalmente, date despre zgarda, medalion, recompense acordate,...) si modalități de contact (minimal, adresa e-mail + numar de telefon). Odată adăugate aceste date, toți utilizatorii aflați în acea zonă vor fi automat notificați. Locul ultimei vizualizări a animalului poate fi actualizat de ceilalți utilizatori, schimbându-se astfel zona și fiind deci notificați alți oameni.

Anunțurile vor fi afisate în ordine cronologica, fiind figurate pe baza unor servicii cartografice OpenStreetMap. Se vor genera statistici de interes -- disponibile in formatele HTML, CSV si PDF -- privind cazurile de animale pierdute, recuperarea acestora, zonele cele mai vulnerabile, recompense etc.

## Arhitectura
### Use cases

1. Userul introduce datele in formularul de login sau in formularul de inregistrare, este redirectionat pe pagina principala, respectiv pe pagina de verificare a mail-ului. I se creaza o sesiune si i se acorda accesul la functionalitatea aplicatiei.

2. Orice utilizator logat poate face generare de statistici la adresa ./statistics si i se ofera posibilitatea de a primi un fisier PDF, CSV sau HTML cu detaliile legate de animalele pierdute.

3. Userul acceseaza pagina de profil pentru a vizualiza animalele pe care le-a gasit sau pe care le-a pierdut.

4. Userul acceseaza sectiunea de notificari, apasa pe “A pet has appeared near you”. De aici user-ul este redirectionat catre pagina animalului pentru a ajuta la gasirea acestuia. De asemenea, pe pagina principala apar in real-time animalele pierdute intr-o raza de 5km (todo: distanta preferentiala a fiecarui user).

5. Userul acceseaza formularul de introducere a unui animal pe care l-a pierdut. Dupa ce introduce toate informatiile necesare este redirectionat pe o pagina noua creata pentru animalul introdus. Utilizatorii pot updata locatia animalului, iar ownerul poate stabili ca un utilizator a gasit animalul.

6. Userul acceseaza site-ul pentru prima oara. Doreste sa isi creeze cont, apasa pe "Sign up" si este redirectionat pe pagina cu formularul de inregistrare. Userul completeaza toate datele necesare, iar la apasarea butonului "Register" un mail de confirmare al contului este trimis pe adresa utilizatorului. Acesta este rugat sa confirme contul inainte de a putea accesa continutul site-ului.

Locatia diagramelor UseCase:
```
./docs/usecase
```
(todo: de modificat diagramele usecase)
### Baza de date

Baza de date va functiona pe baza join-urilor si relatiilor intre doua entitati(tabele) normalizate, anume user si pet.
Userii, la inregistrare, vor populariza tabela "user".
Legatura dintre user si pet se va face in mai multe moduri in functie de rolul pe care il are un anumit user fata de un anunt.
Astfel userii pot fi:

1. Owneri - persoana care a pierdut animalul si l-a postat prin intermediul formularului;
persoana cu acest rol popularizeaza tabela "pet"

2. Helperi - persoanele care dau update la anunt in cazul in care l-au vazut sau l-au gasit; acestia pot adauga locatii in care au vazut animalul.

Locatiile utilizatorilor vor fi stocate sub forma de coordonate "LAT:243 LNG:36" in tabela "location". Astfel, utilizatorul primeste pe pagina principala locatia in care a fost declarat animalul pierdut iar cand acceseaza pagina specifica acelui animal se observa si alte update-uri ale altor utilizatori. In cazul in care owner-ul declara animalul gasit de un utilizator X, atunci animalul devine found si nu o sa mai apara in pagina principala.


NOTIFICARI
Tabela "update_on_ad" este folosita pentru notificarile de tipul "Userul Y a raspuns la un anunt de al tau" si se foloseste de id-urile celor 2 useri si de id-ul petului pentru a identifica corect persoanele si animalul despre care este vorba.

Tabela "owner" are rol de legatura intre "user" si "pet", si este folosita pentru notificarile de tipul "Pet Z has been found thanks to you".

Pentru generarea statisticilor se vor folosi operatii de tipul select pe baza de date.

Diagrama bazei de date se afla la adresa:
```
./docs/database
```
(todo: update database schema)

### APIs

1. LostPets API
API-ul creat de noi pentru a ne servi in preluarea si prelucrarea datelor. Parsarea datelor se face dupa standardele OpenAPI prin intermediul JSON-urilor. In cadrul API-ului am declarat urmatoarele rute ce satisfac urmatoarele obiective:

```
RewriteRule ^get/loc/.+$ ./control/api/api.get.php [NC,L]
```
Pentru a prelua animalele de la o anumita locatie actuala a utilizatorului vom apera ruta cu argumentele /get/loc/{latitudine}/{longitudine}.
Acest fetch se va face de fiecare data cand locatia utilizatorului se modifica. API-ul furnizeaza un obiect ce contine informatiile necesare afisarii animalelor pe pagina principala a aplicatiei. Metoda de apelare este GET.

```
RewriteRule ^get/profile/.+$ ./control/api/get-profile.php [NC,L]
```
Pentru a putea accesa fiecare profil in parte avem nevoie de o rutare a acestora. Partea de profil a API-ului returneaza din baza de date un fisier ce contine toate informatiile si animalele unui utilizator pentru a putea fi folosite de aplicatia noastra. Drept argument se va da id-ul utilizatorului sub forma /get/profile/{id} si metoda este GET.

```
RewriteRule ^get/pet/.+$ ./control/api/api.pet.php [NC,L]
```
De asemenea, pentru a putea accesa informatiile unui animal despre care nu avem contextul unui utilizator/owner, aparea API-ului la aceasta ruta va avea acelasi efect ca la /get/profile/ doar ca in cazul unui animal. Apelul se va face prin /get/pet/{id} si metoda este GET.

```
RewriteRule ^get/notifications/.+$ ./control/api/api.notif.php [NC,L]
```
Fetch-ul asyncron constant ce se face la modificarea locatiei utilizatorului va apela si partea de notificari a API-ului ce va returna un fisier cu informatiile necesare afisarii unei notificari.

2. DogCEO:
Functioneaza pe baza de fetch-uri la adresa:
```
fetch('https://dog.ceo/api/breeds/list/all')
```
Astfel, in cazul introducerii unui animal in formular, la selectarea rasei canine, API-ul trimite toate rasele si le introduce intr-un select, simplificand enorm timpul necesar pentru hard-codarea optiunilor din select.
Acest API nu necesita nici o cheie si respecta standardul OpenAPI, fiind foarte rapid si usor de folosit.
```
 Object.keys(response.message).forEach(function(value, i) {
        let option = document.createElement('option');
        option.text = value;
        breed.append(option);
    });
```
Raspunsul primit prin GET este unul de tip JSON iar in interiorul obiectului avem campul message al carui valoare este rasa pe care o appendeaza la optiuni.

3. TheCatAPI
Asemanator API-ului anterior, functioneaza tot pe baza de fetch-uri:
```
fetch('https://api.thecatapi.com/v1/breeds')
```
Este diferit, insa, prin faptul ca necesita o autentificare pentru a putea primi rezultatele.
Folosirea acestor API-uri faciliteaza extinderea in viitor pentru a adauga alte rase de animale de companie, insa ajuta si la generarea de statistici deoarece o sa fie standardizate si centralizare pe acelasi tip de rasa si specie, utilizatorul neavand optiunea de a scrie propria rasa de animal pierdut.

4. Leaflet
Leaflet reprezinta o librarie de harti open-source ce aduce developerilor optiuni de creare a unei harti pe care se pot adauga markere, cu care se poate lua locatia unui utilizator si cu care se pot pozitiona elemente si seta optiuni necesare functionarii aplicatiei.
Pentru functionarea librariei leaflet trebuie introduse urmatoarele linii in head:
```
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css" integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ==" crossorigin="" />
<script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js" integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og==" crossorigin=""></script>
```
Acum in interiorul fisierelor javascript vor putea fi construite si prelucrate hartile din libraria Leaflet:
```
mymap = L.map('mapid');
```
Astfel, locatia utilizatorilor este luata constant si se furnizeaza animalele din raza lor pe baza LostPetsAPI.
```
latlng = new L.LatLng(location.coords.latitude, location.coords.longitude);
```

### Autentificare persistenta

Se va realiza prin folosirea sesiunilor si cookie-urilor.

1. Inregistrarea se va face prin intermediul metodei 
```
.createUserWithEmailAndPassword(email,password)
```
Parola va fi hash-uita prin algoritmul sha256.
2. Dupa inregistrare utilizatorul este logat automat si i se atribuie o sesiune ce contine un id unic si id-ul sau ca utilizator din baza de date.
3. Pentru fiecare operatiune desfasurata in interiorul aplicatiei (inclusiv rutarea rutelor) se vor interoga si se vor prelua detalii din sesiune
```
if(isset($_SESSION['UID])) header('location ...);
```
4. In cazul delogarii sesiunea va fi steasa revocandu-se acordul la orice ruta senzitiva sau la care un utilizator trebuie sa fie logat pentru a avea acces.
```
session_destroy();
```

### MVC Design Pattern

Datele din aplicatie vor fi structurate pe baza MVC design pattern astfel:
Utilizatorii vor avea acces la folder-ul public unde este stocat codul de view al aplicatiei.
Rutarea se va face prin intermediul fisierului .htaccess pentru ca utilizatorul sa nu vada calea actuala catre fisierele pe care le apeleaza.
In folderul ./control/ vor fi salvate fisierele de tip controller si modelul ce va interactiona cu baza de date.
Astfel, utilizatorul primeste doar view-ul si majoritatea interogarilor cu baza de date se vor face prin intermediul controllerelor.
Drept entitati ale modelelor avem:
```
db.php
user.php
pet.php
```
pe care le vom folosi pentru a crea,sterge,updata si citi datele ce sunt salvate in baza de date mysql.
Orice form prezent pe pagina va apela astfel un controller ce va apela un model interogand baza de date. Baza de date furnizeaza un raspuns ce vine la model, este verificat in controller si este primit pe view pentru a fi prelucrat si oferit utilizatorului.
Exceptia de la regula este realizata de interogarile la API-ul creat de aplicatie, deoarece in acest caz view-ul apeleaza un fisier javascript ce realizeaza un fetch asincron pentru updatarea informatiilor din pagina fara a fi necesar un refresh. Astfel, serverul ofera informatiile API-ului, API-ul trimite informatiile in view si la nivel client se vor realiza majoritatea prelucrarilor si verificarilor.

Diagramele MVC se gasesc la locatia:
```
./docs/mvc
```

## Documentatie

* [Leaflet](http://leafletjs.com)
* [ECMAScript 6](http://es6-features.org/#Constants)
* [DogCEO](https://dog.ceo)
* [TheCatAPI](https://thecatapi.com)
* [CDN](https://www.cloudflare.com/learning/cdn/what-is-a-cdn/)

## Autori

* **Radu Emilian & Piuco Andrei** - LostPets