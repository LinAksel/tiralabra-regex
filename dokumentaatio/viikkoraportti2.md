# Viikko 2

### Päiväkirja/muistiinpanot

#### Maanantai

Luin lähteissä mainittua Russ Coxin artikkelisarjaa, ja koetin ymmärtää siitä enemmän.
Käytännön toteutus tuntuu vielä hieman kaukaiselta, mutta aihe on kiinnostava ja ymmärrän koko ajan hieman lisää.

#### Tiistai

Palautteessa mielenkiintoinen huomio tulkin ja kääntäjän erosta säännölisten lauseiden osalta.
Tulkkiversio on ilmeisesti rekursiivinen "syötteensyöjä", joka ei vaatisi automaattien rakennusta, ja voisi mahdollistaa takaisinviittauksen helpommin.
Automaatiksi ensin käännettäessä taas vaikuttaa lähteiden perusteella olevan etuna se, että kaikki tunnistetaan lineaarisessa ajassa, eikä mikään syöte voi rikkoa ohjelmaa, kuten rekursiossa ja takaisinpaluussa.
Näiden kahden erojen testaaminen voisi olla projektin fokus? Toki oletuksella, että kummankin lähestymistavan algoritmin saisi rakennettua kurssin aikataulussa.

#### Torstai

Ensimmäinen rekursiivinen ei-automaattiversio toimii määräoperaattoreilla *, ? ja +! Voi olla, että tämä malli tulee kaatumaan ongelmiin,
mutta oli mielenkiintoista alkaa rakentamaan jotain konkreettista. Kääntäjä vs. tulkki tuntuu tällä hetkellä mahdolliselta tavoitteelta, mikäli jo ensi viikolla pääsisin
kääntäjän tekoon.

#### Perjantai

Lopulta toimii myös tai-merkki | ja hieman myös sulkeiden käsittely. Pidän mahdollisena, että sulkeita varten joudun muuttamaan ohjelman pohjaa jonkin verran,
mutta uskon silti saavani myös ne toimimaan pian.

## Tehdyt työt ja edistyminen

Kuten päiväkirjasta tulee ilmi, ensimmäinen kokeilu tulkista toimii jo osittain. Suurin osa internetistä löytyvästä materiaalista käsittelee yksinomaan
automaattien simuloinnin avulla tapahtuvaa säännöllisten lauseiden kääntöä, joten tämä ei-automaattiversio on pitkälti omaa kokeilua.
Olen kuitenkin varsin tyytyväinen tahtiin, sillä tiedän nyt varmasti saavani ainakin yhden toimivan algoritmin tehtyä (sillä automaattityylistä löytyy paljon enemmän
avustavaa materiaalia).

## Epäselvyyksiä

Suurin epäselvyys itselläni on palautteessakin mainittu projektin koko. Lähtökohtaisesti tähtään mahdollisimman hyvään arvosanaan, onko kahden eri tavalla rakennetun
(tulkki vs kääntäjä) regex-lukijan vertailu tarpeeksi, liikaa vai liian vähän tavoitteeseen nähden? Tässä tapauksessa en tietenkään lähtisi implementoimaan kovin
vaikeita ominaisuuksia. Yhteen kääntäjään/tulkkiin panostaminen voisi tarkoittaa vaikeampien ominaisuuksien (viittaukset, vilkaisut, ryhmät yms.) yrittämistä,
mutta mahdollisesti myös liian suppeaa projektia, mikäli en saisi näitä toimimaan. Tästä kaipaisin siis mielipiteitä!

## Mitä opin ja mitä teen seuraavaksi

Olen jälleen oppinut lisää erilaisia tapoja hahmotella regex-kääntäjän mallia ohjelmaksi (lähinnä automaattipohjaisia). Seuraavaksi koetan saada perustoiminnot valmiiksi
aloittamallani tulkilla, tai vaihtoehtoisesti keksin mahdollisimman nopeasti miksi tämä ei ole mahdollista ja muutan ohjelmaa. Joka tapauksessa ensi viikolla on luvassa
koodin laajentamista, siistimistä ja ehkä jo tarvittavien lisätietorakenteiden erottelua ohjelmasta.
