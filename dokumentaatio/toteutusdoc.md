# Regex-tulkki

## Toiminta

Ohjelman tarkoitus on tutkia, sisältyykö syöte annetun säännöllisen lausekkeen muodostamaan kieleen.
Käyttäjä syöttää ensin lausekkeen, sitten syötteitä, ja ohjelma kertoo sisältyykö syöte kieleen.
Kielen voi muodostaa kaikista utf-8-merkeistä käyttäen metamerkkejä *,?,+,|,\\,. (ja ()). Merkeistä ja niiden käytöstä lisää käyttöohjeessa.

Pääpiirteittäin ohjelma toimii tarkistamalla ensin säännöllisen lauseen oikeellisuuden,
jonka aikavaativuus on aina O(r), jossa r on säännöllisen lauseen pituus, sillä regex luetaan oikealta vasemmalle kokonaisuudessaan tasan kerran.
Tämän jälkeen aletaan rakentaa säännöllistä lausetta lopusta alkuunpäin lukemalla testimerkkijonoja,
joita verrataan syötteeseen. Mikäli missään kohtaa rakennetaan syötteen lopusta poikkeava merkkijono, ohjelma keskeyttää kyseisen haaran,
ja koettaa jatkaa muita. Muutkin jonot voivat kuitenkin kokeilla saman jonon rakentamista eri kohdissa, joten aika-analyysiksi vaikuttaa tulevan
O(r*s*m), jossa r on säännöllisen lausekkeen pituus, s syötteen pituus ja m kaikkien merkkien (utf-8) määrä.
Vaikka pahin tapaus on kaukana lineaarisesta, käytännössä kuitenkin regexin sisältö vaikuttaa tähän todella paljon,
ja tulkki toimii lähes kaikissa käytännön tapauksissa hyvinkin nopeasti.

## Puutteet ja parannukset

Ohjelmasta puuttuu lukemattoman paljon nykyisten modernien regex-ohjelmien käyttämiä erikoistunnisteita,
mutta nämä eivät monestikkaan teoreettisesti laajenna tunnistettujen kielten joukkoa, vaan helpottavat ohjelmoijaa.
Yleisesti käytetty takaisinviittaus kuitenkin laajentaisi tulkkia yli säännöllisten kielten, ja voisi olla selkeä parannusehdotus tulevaisuuteen, mikäli
projektia haluaisi jatkaa kurssin jälkeenkin.
