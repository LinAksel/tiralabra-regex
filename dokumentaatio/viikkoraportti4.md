# Viikko 4

### Päiväkirja/muistiinpanot

#### Keskiviikko

Iso bugi löytyi jo alkuviikosta, sisäkkäiset sulkeet voivat jäädä ylivuotoon johtavaan luuppiin mikäli käytetään Kleenen tähteä.
Ongelma tuntuu olevan suhteellisen vaikea ratkaista, "toteutusvelka" alkaa ottaa selvästi kiinni.
Uskon kuitenkin keksiväni jotain, tänään sain selville jo hieman enemmän tutkimalla pari tuntia rekursion kulkua.

#### Torstai

Massiivinen edistyspäivä, joskin myös tuntimäärältä tähän mennessä pisin. Bugi on nyt korjattu hyvin mielenkiintoisella tavalla,
jota koetan huomenna avata dokumentoinnissa tarkemmin. Tämän jälkeen löytyi joukko "pienempiä" bugeja, jotka sain myös korjattua samalla draivilla.
Näiden korjausten jälkeen en ole saanut tulkkia enää hajoamaan, vaan Javan Stringbuilder tulee ensin vastaan. Tyhjä kieli voidaan nyt syöttää yksinkertaisesti painamalla enteriä regexin syötössä,
ja tyhjä syöte vastaavasti samalla tavoin. Pisteoperaattori vaikuttaa nyt toimivan kaikilla merkeillä, ja escape-toiminnallisuus on lisätty kaikkiin
operaattoreihin. Rakensin myös päivän lopuksi nopeasti säännöllisen lauseen tarkastajan, joka tutkii, että annettu regex on perussääntöjen mukainen.
Huomenna on todennäköisesti myös pitkä päivä, sillä edessä on koodin siistimistä, testien tekoa ja dokumentointia. Toisaalta on myös ilon asia,
että itse toiminnallisuus on nyt jo todella pitkällä, ja voin keskittyä rauhassa kaikkeen muuhun hetkisen.

#### Perjantai

Testien tekemisen myötä bongasin muutaman pienen lisäbugin, esim. backslashia ei voinut käyttää itseensä, mutta jälleen ohjelma toimii hieman paremmin!
Regexin oikeellisuustarkastajaa tulee ehdottomasti siistiä, mutta siihenkin luotu nyt testit, joten itse toiminnallisuus vaikuttaa hyvältä.
Suorituskykytestausta on pohdittava ensi viikolla kunnolla, rekursiivinen luonne aiheuttaa helposti liian pitkillä syötteillä ylivuotoja.
Tätä ei kuitenkaan voi kovin helposti kiertää, vaan se on "ominaisuus".

## Tehdyt työt ja edistyminen

Tällä viikolla sain korjattua ohjelmassa esiintyneitä pahoja ongelmia, ja lisättyä kaksi uutta erikoismerkkiä, '.' ja '\'!
Tämän lisäksi myös säännöllisen lauseen esitarkastaja on nyt rakennettu alustavasti, ja vaikuttaa toimivan suhteellisen hyvin.
Lisäksi testaus on edistynyt vihdoin kattamaan uusia ominaisuuksia.
Edistys oli siis hyvin konkreettista, ja olen todella tyytyväinen viikkoon!

## Epäselvyyksiä

Kommentit labtoolissa ovat olleet loistavia, ja epäselvyydet hyvin selätetty monessa kohtaa!
Tällä hetkellä ei itse asiassa mieleeni tule mitään kurssin suorittamisen kannalta epäselvää.
Itse ohjelman kanssa on vielä paljonkin selvitettävää, sillä nyt kun toiminnallisuus on itsessään jo riittävän tuntuisella tasolla,
on tutkittava tarkasti erilaisia toteutuksen mukanaan tuomia ongelmia ja rajoitteita.

## Mitä opin ja mitä teen seuraavaksi

Viikon tärkeä oppi oli, että debugatessa parasta on nähdä ohjelman tila joka muutoksen jälkeen. Kun aloin seurata muuttujien tiloja rekursion eri vaiheessa,
sain paikannettua usean hyvin monimutkaisen ongelmatapauksen, joita oli lähes mahdotonta vain "pohtia läpi". Ensi viikolla aion jatkaa tämän opin kanssa,
ja kehittää omituisia, kattavia ja monimutkaisia testejä, jotka paljastavat mahdollisesti lisää virhetoimintaa. Myös selkeän käyttöohjeen luominen olisi paikallaan,
ja mahdollisesti UI:n päivitys graafiseen, jos aikaa jää.

