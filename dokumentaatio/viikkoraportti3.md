# Viikko 3

### Päiväkirja/muistiinpanot

#### Keskiviikko

Suuri milestone vaikuttaa saavutetulta, sillä tulkki tuntuu toimivan nyt myös sulkujen kanssa oikein kaikilla tällä hetkellä tunnistetuilla lausekkeilla!
Jännittävää oli huomata, että rekursion käsittelyjärjestyksellä oli todella paljon väliä,
stack overflow seurasi testatessa helposti mikäli toistoja ei tehty viimeisenä.
Seuraavaksi vuorossa kattavia testejä varmistamaan että kaikki todella toimii, ja mahdollisesti refaktoroinnin aloittamista, nyt unta.

#### Perjantai

Nyt on toiminnallisuudesta erotettu konsolikäyttöliittymä, joka helpottaa mahdollisesti jatkotestausta, kun samaan regexiin voisi toistaa useita sanoja.
TestiIO pitää kuitenkin tätä varten muuttaa, se ja jo mainitut testit ja siistiminen ovat seuraavaksi edelleen vuorossa. Mikäli kaikki toimii,
ehdin mahdollisesti rakentaa vielä lisätoiminnallisuutta, sillä varsinaisesti mitään toteutettavaa tietorakennetta ei vielä ole.
Tietysti jos haluan käyttää jossain kohdin dynaamista listaa, voisin rakentaa ArrayList-tyyppisen rakenteen? Joka tapauksessa projekti tuntuu etenevän hyvin.

## Tehdyt työt ja edistyminen

Tällä viikolla sain sulkeet toimimaan muiden operaattorien ja sisäkkäisyyksien kanssa pitkälti oikein!
Myös manuaalista testausta ja käyttöä helpottava komentorivikäyttöliittymä. Projekti edistyy siis aikataulun suhteen hyvin.

## Epäselvyyksiä

Kiitos selkeistä ja pitkistä palautteista viime viikon epäselvyyksissä! Tällä viikolla fokus tuntuu olevan selkeä, eli pyrin rakentamaan mahdollisimman
hyvän ja toimivan tulkin. Näyttää kuitenkin siltä, että toistaiseksi en ole juuri joutunut käyttämään tietorakenteita joita korvata omilla toteutuksilla.
Voi olla, että jonkinlaista listaa tullaan vielä tarvitsemaan, mutta onko projekti riittävä, mikäli se pyörii jo alusta asti ilman monimutkaisempia rakenteita?

## Mitä opin ja mitä teen seuraavaksi

Opin ajattelemaan muutamalla uudenlaisella tavalla rekursion käyttöä, sillä ohjelmakokonaisuus on hyvin herkkä rekursion sisäisille järjestyksille.
Seuraavaksi jatkan debuggausta, jonka opin olevan työläämpää kuin kuvittelin, ja oletan että ensi viikko kuluu paljolti bugien etsimisessä ja niitä korjatessa.
Myös simppeli ikkunakäyttöliittymä voisi olla hyvä, jotta testaus ja käyttö helpottuu edelleen.
