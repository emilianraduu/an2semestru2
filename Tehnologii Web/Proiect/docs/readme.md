# Lost Pets Architecture

Proiect la materia Tehnologii Web ce consta intr-o aplicatie web pentru persoanele care si-au pierdut animalul de companie.

## Enunt proiect

Creați o aplicație Web care să vină în ajutorul recuperării animalelor pierdute de o persoana. Proprietarul animalului pierdut va completa un formular cu detalii despre acesta: nume, fotografie, zona în care a fost ultima dată văzut, plus alte detalii de interes (specie, existenta unor semnalmente, date despre zgarda, medalion, recompense acordate,...) si modalități de contact (minimal, adresa e-mail + numar de telefon). Odată adăugate aceste date, toți utilizatorii aflați în acea zonă vor fi automat notificați. Locul ultimei vizualizări a animalului poate fi actualizat de ceilalți utilizatori, schimbându-se astfel zona și fiind deci notificați alți oameni.

Anunțurile vor fi afisate în ordine cronologica, fiind figurate pe baza unor servicii cartografice OpenStreetMap. Se vor genera statistici de interes -- disponibile in formatele HTML, CSV si PDF -- privind cazurile de animale pierdute, recuperarea acestora, zonele cele mai vulnerabile, recompense etc.

## Arhitectura
### Use cases

1. Userul introduce datele in formularul de login, este redirectionat pe pagina principala, de unde selecteaza un animal pe care l-a vazut. Face un update la pagina animalului.

2. Adminul site-ului acceseaza pagina de login administrativ, ii apare pagina de monitorizare a aplicatiei web de unde genereaza statistici in format pdf a numarului de animale pierdute si recuperate in Nicolina.

3. Userul acceseaza pagina de profil pentru a modifica detalii precum poza de profil, adresa de email si pentru a vizualiza animalele pe care le-a gasit sau pe care le-a pierdut.

4. Userul acceseaza sectiunea de notificari, apasa pe “[Pet name] has been found thanks to your contribution!”. De aici user-ul este redirectionat catre pagina animalului pe care l-a gasit pentru a-si lua recompensa in cazul in care exista.

5. Userul acceseaza pagina de introducere a unui animal pe care l-a pierdut. Dupa ce introduce toate informatiile necesare este redirectionat pe o pagina noua creata pentru animalul introdus. Cativa alti useri updateaza locatia animalului, iar un altul il gaseste.

6. Userul acceseaza site-ul pentru prima oara. Doreste sa isi creeze cont, apasa pe "Sign up" si este redirectionat pe pagina cu formularul de inregistrare. Userul completeaza toate datele necesare, iar la apasarea butonului "Register" un mail de confirmare al contului este trimis pe adresa utilizatorului. Acesta este rugat sa confirme contul inainte de a putea accesa continutul site-ului.

Locatia diagramelor UseCase:
```
./docs/usecase
```

### Baza de date

Baza de date va functiona pe baza join-urilor si relatiilor intre doua entitati(tabele) normalizate, anume user si pet.
Legatura dintre user si pet se va face in mai multe moduri in functie de rolul pe care il are in legatura cu entitatea pet.
Astfel userii pot fi:
1. Owneri - persoana care a pierdut animalul si l-a postat prin intermediul formularului
2. Helperi - persoanele care dau update la anunt in cazul in care l-au vazut sau l-au gasit
Locatiile utilizatorilor vor fi stocate
Diagrama bazei de date se afla la adresa:
```
./docs/database
```

### APIs

1. Firebase API
SDK-ul Firebase este preluat prin introducerea in meta-ul HTML-ului a CDN-ului

```
<script src="https://www.gstatic.com/firebasejs/5.9.3/firebase.js"></script>
```
1. Pentru a se realiza conectarea cu baza de date se va folosi:
```
/firebase:
  get:
  /{apiKey}:
    get:
  /{authDomain}:
    get:
  /{projectId}:
    get:
  post:
    /{dbID};
```
2. Pentru a adauga utilizatori se va folosi:
```
/firebase:
  get:
  /{users}:
    post:
    /add
```
3. Pentru toate celelate operatiuni de insert/update in baza de date vor fi folosite REST-urile firebase din documentatie.


2. OpenStreetMap API
Se va folosi de Maker API, overlay al Map API prin intermediul librariei JS Leaflet.
1. Pentru a lua locatia utilizatorului la momentul conectarii se va folosi:
```
/map:
  get:
  /{location}:
  post:
  /{map}:
    get:
    /zoom:
      get:
      post:
    get:
    /center
      get:
      post:
```
2. Pentru a se adauga pin-uri la locatiile in care s-au pierdut animalele de companie se va folosi:
```
/map:
  get:
  /{location}:
  post:
  /{map}:
    get:
    /zoom:
      get:
      post:
    get:
    /center
      get:
      post:
    get:
    /icon
      get:
      post:
    get:
    /dragging:
      get:
      post:
```
3. De asemenea salvarea si restaurarea se va face tot prin intermediul Leaflet API folosind layers.

### Autentificare persistenta

Se va realiza prin folosirea tool-ului Firebase Authentification.

1. Inregistrarea se va face prin intermediul metodei 
```
.createUserWithEmailAndPassword(email,password)
```
2. Dupa inregistrare utilizatorul este logat automat.
3. Salvarea sesiunii si modificarilor din cadrul sesiunii se va face folosind metoda 
```
.onAuthStateChanged(firebaseUser => {})
```
4. Logarea in cazul delogarii se va face folosind metoda
```
.signInWithEmailAndPassword(email,password)
```

### ModelViewController Design Pattern

Datele din aplicatie vor fi structurate pe baza MVC design pattern astfel:
Utilizatorii vor avea acces la folder-ul public unde este stocat codul de view al aplicatiei.
Fiecare pagina va fi de tipul .php si va include metode din controller ce va apela modelul pentru legatura cu baza de date.
Accesul utilizatorilor se va face pe baza unui fisier .htaccess ce va avea specificate relatiile dintre view si controller.
Va exista un folder control ce va contine clasele necesare aplicatiei, spre exemplu:
```
profil.php va apela functia user si metoda checkIfLogged(id_session) si getUser(id_session)  prin API-ul firebase.
```
In folder-ul model se va rezolva cerinta metodelor apelate de profil fiind legatura intre baza de date si aplicatie.
Diagramele MVC se gasesc la locatia:
```
./docs/mvc
```

## Documentatie

* [Firebase](https://firebase.google.com/docs/firestore/quickstart?authuser=0)
* [Firebase JS](https://firebase.google.com/docs/reference/js/)
* [OpenStreetMap](https://wiki.openstreetmap.org/wiki/API_v0.6)
* [Leaflet](https://leafletjs.com/reference-1.4.0.html#map-example)
* [CDN](https://www.cloudflare.com/learning/cdn/what-is-a-cdn/)
* [MapBox](https://docs.mapbox.com/api/maps/)

## Autori

* **Radu Emilian & Piuco Andrei** - LostPets