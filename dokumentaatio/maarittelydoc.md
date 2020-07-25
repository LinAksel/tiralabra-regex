# RegEx

Projektin tarkoituksena on kehittää ja vertailla muutamaa erilaista säännöllisten lauseiden tulkkia.
Käyttäjä syöttää ohjelmalle ensin säännöllisen lauseen, ja sitten tekstiä/tiedoston, josta ohjelman on tarkoitus löytää ilmaisua vastaavat kohdat.
Projektissa vertailen erilaisia toteutuksia tulkista, ja pyrin luomaan mahdollisimman monipuolisen ja optimoidun "päätulkin".
Käytettävä kieli tulee olemaan Java.

### Vaativuudet 

Käännettäessä epädeterministinen automaatti (NFA) kokonaisuudessaan deterministiseksi (DFA) aika- ja muistivaatimus on O(2<sup>m</sup>), jossa m on säännöllisen lauseen pituus,
mutta vertailujen aika on O(n), jossa n on vertailtavan merkkijonon pituus. Jos NFA:ta vain simuloidaan rakentamalla DFA:n tilat vain tarvittaessa,
on rakennusvaatimus pienempi, mutta vertailujen aikavaatimus kasvaa O(nm)-aikaan. Näiden perustyylien lisäksi on mahdollista optimoida algoritmejä edelleen,
ja tavoitteenani on luoda päätulkista aikavaatimukseltaan mainittuja parempi.

### Miksi?

Laskennan mallit-kurssi oli mielestäni kiinnostava ja merkkijonoalgoritmit alkoivat kiinostaa enemmän Algoritmit ongelmanratkaisussa-kurssin aikana, joten säännöllisten lauseiden tulkin toteutus tuntuu sopivalta jatkolta näile.
Haluan myös oppia ymmärtämään säännöllisten lauseiden koostamista paremmin, ja tämä projekti osaltaan pakottaa siihen.

## Lähteet

* [https://en.wikipedia.org/wiki/Regular_expression](https://en.wikipedia.org/wiki/Regular_expression)

* [https://www.cs.helsinki.fi/u/hisahi/sanastot/regex.html](https://www.cs.helsinki.fi/u/hisahi/sanastot/regex.html)

* [https://swtch.com/~rsc/regexp/regexp1.html](https://swtch.com/~rsc/regexp/regexp1.html)

