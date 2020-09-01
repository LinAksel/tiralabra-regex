# RegEx-tulkki

Projektin tarkoituksena on kehittää toimiva ja käytännön tilanteissa nopea säännöllisten lauseiden tulkki. Kyseessä on nimenomaan tulkki, eli ohjelma ei rakenna automaattia (NFA/DFA) annetusta lausekkeesta, vaan pyrkii rakentamaan käyttäjän antaman, vertailtavan merkkijonon "syömällä" säännöllistä lauseketta, ja rakentamalla mahdollisia lausekkeen muodostaman säännöllisen kielen yksittäisiä merkkijonoja. Toiminnallisuuksiltaan tulkki on rajoitetumpi kuin monet modernit regex-moottorit, mutta toteuttaa kuitenkin vähintään kaikkien säännöllisten kielten tunnistamiseen vaaditut toiminnallisuudet. Projektin ohjelmointikielenä on Java, pitkälti tuttuuden ja muillakin kursseilla (toistaiseksi) käytön takia.

### Tietorakenteet

Näyttää siltä, että projekti on mahdollista toteuttaa käyttämällä vain Javan perustietorakenteita String, Boolean, int ja char, sekä perustaulukkoa näistä (esim. int[]). Tämä voi kuitenkin muuttua projektin edetessä, ja tällöin tarvittavia tietorakenteita toteutetaan tarpeen mukaan itse.

### Käyttö

Käyttäjä syöttää ohjelmaan aina kaksi eri merkkijonoa: säännöllisen lausekkeen ja tähän verrattavan merkkijonon. Tämän jälkeen ohjelma kertoo, kuuluuko annettu merkkijono lausekkeen muodostamaan säännölliseen kieleen.

### Vaativuudet

Ohjelman teoreettinen aikavaativuus rekursiototeutuksella on O(n<sup>3</sup>). Tämä johtuu siitä, että Kleenen tähti kohdattaessa joudutaan haarautumaan kolmeen: lisätään ja jäädään tähteen, lisätään ja jatketaan eteenpäin, ei lisätä ja jatketaan. Tämä voidaan kuitenkin korjata käytännön tilanteisiin peruuttavaa hakua optimoimalla niin, että kaikkia haaroja ei käydä loppuun asti, vaan yksikin väärä merkki katkaisee vertailun, samoin merkkijonoa pidemmäksi edennyt vertailu. Käytännön tilanteissa tulkin tulisi toimia siis hyvinkin nopeasti.

### Lähteet

* [https://en.wikipedia.org/wiki/Regular_expression](https://en.wikipedia.org/wiki/Regular_expression) Yleistä säännöllisistä lausekkeista

* [https://www.cs.helsinki.fi/u/hisahi/sanastot/regex.html](https://www.cs.helsinki.fi/u/hisahi/sanastot/regex.html) Suomenkielistä RegEx-sanastoa

* [https://swtch.com/~rsc/regexp/regexp1.html](https://swtch.com/~rsc/regexp/regexp1.html) Loistava artikkelisarja RegEx-moottorien toteuttamisesta

* [https://www.rexegg.com/regex-quickstart.html](https://www.rexegg.com/regex-quickstart.html) Kattava ja selkeä listaus eri toiminnoista

[Linkki kurssin alussa tehtyyn, vanhentuneeseen dokumenttiin](/dokumentaatio/arkisto/maarittelydocOLD.md)
