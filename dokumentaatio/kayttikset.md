# Käyttöohjeet


Regex-tulkkia käytetään testaamaan, kuuluuko jokin merkkijono annettuun säännölliseen lausekkeeseen (kieleen).

## Käynnistäminen

Ohjelma voidaan käynnistää joko lataamalla lähdekoodi, ja ajamalla kansiossa

```
mvn compile exec:java -Dexec.mainClass=regex.Main
```

TAI

lataamalla uusin release, ja käynnistämällä suoraan .jar-tiedosto komennolla

```
java -jar RegexEngine.jar
```

## Toiminta

Merkkijonoon voi kuulua mitä tahansa utf-8:n mukaisia merkkejä, kuten myös kieleen. Niinpä esimerkiksi säännöllinen lause 'a@?x'
vastaa kieltä {ax, a@x}. Käytettävissä on joukko erilaisia erikoismerkkejä:

### ?
 * Merkki tarkoittaa "kerran tai ei ollenkaan". Siispä esimerkiksi lauseke 'aa?' tarkoittaa joko merkkijonoa 'a' tai 'aa', sillä toinen 'a' voi toistua joko kerran tai ei ollenkaan.

### * 
 * Merkki tarkoittaa mielivaltaista määrää toistoja, myös nolla kertaa. Niinpä esimerkiksi regex 'a*' vastaa joukkoa {\\e, a, aa, aaa, aaaa, ...}, jossa \\e on tyhjä merkkijono.

### \+
 * Merkki tarkoittaa yhtä tai useampaa toistoa. Tällöin 'a+' vastaa merkkijonojoukkoa {a, aa, aaa, aaaa, aaaaa, ...}.

### ()
 * Sulkeet tarkoittavat ryhmää, ja toimivat kuin matematiikassa. Ryhmiä voidaan käsitellä kuin yksittäisiä merkkejä niin, että esimerkiksi 'aivo(kapasiteetti)?' vastaa merkkijonoja 'aivo' ja 'aivokapasiteetti'.

### |
 * Merkin merkitys on "tai". Niinpä 'a(bb|ll)a' vastaa kahta merkkijonoa, 'abba' ja 'alla'.

### .
 * Merkki tarkoittaa mitä tahansa merkkiä. Siispä esimerkiksi lausekkeen '.*' tulisi hyväksyä mikä tahansa merkkijono.

### \\
 * Merkki tarkoittaa erikoismerkistä poistumista. Nyt siis 'a\\\*' tarkoittaa vain yhtä merkkijonoa, 'a\*', sillä '*' ei lasketa erikoismerkiksi. (Myös '\\\\' on '\\', eli merkki toimii itseensä)

### \\e
 * Erikoispalautus \\e tarkoittaa merkkijonossa tyhjää. Tällöin a(\\e|bb)a vastaa kahta merkkijonoa, 'aa' tai 'abba'.

### \\d
 * Erikoispalautus \\d tarkoittaa merkkijonossa numeroa 0-9. Esimerkiksi a\\db vastaa merkkijonoja {a0b, a1b, a2b, ... a9b}.
## Käyttöliittymä

Kun ohjelma käynnistetään, aukeaa käyttöliittymäikkuna:

| ![alku.png](https://raw.githubusercontent.com/LinAksel/tiralabra-regex/master/dokumentaatio/kuvat/alku.png) |
|:--:|
| *1. Säännöllisen lausekkeen kenttä* |
| *2. Merkkijonon kenttä*  |
| *3. Tämänhetkinen säännöllinen lauseke*  
| *4. Loki-ikkuna* |

Säännöllisen lausekkeen kenttään syötetään haluttu lauseke, aluksi oletuslauseke on tyhjä \\e. Kun regex-kenttään syötetään hyväksytty lauseke, esim. 'abba', ja painetaan joko 'Enter regex'-nappia tai enter-näppäintä, päivittyy uusi lauseke sekä lokiin että keskelle (kohta 3). Nyt voidaan kokeilla verrata merkkijonoa annettuun lausekkeeseen:

![oikein.png](https://raw.githubusercontent.com/LinAksel/tiralabra-regex/master/dokumentaatio/kuvat/oikein.png)

Kuvassa on syötetty regexiä vastaava merkkijono 'abba', jolloin lokiin ilmestyy hyväksytty merkki, ja taustaväri muuttuu vihreäksi.

![vaarin.png](https://raw.githubusercontent.com/LinAksel/tiralabra-regex/master/dokumentaatio/kuvat/vaarin.png)

Nyt merkkijono ei vastaa lauseketta, vaan lokiin ilmestyy hylätyy merkki ja taustaväri muuttuu punaiseksi. Käyttöliittymä kertoo siis sekä taustavärillä että lokilla, täsmääkö merkkijono säännölliseen lausekkeeseen. Molempien syöttönappien takana on lisäksi pieni nuolinappi, jolla on mahdollista palauttaa tekstikenttään edellinen syöte.

## Vikatilanteet

+ jos huomaat, että tulkki ei toimi oikein (esimerkiksi lausekkeella 'a+b' ei hyväksyttäisi merkkijonoa 'aab'), avaathan uuden issuen, jossa kerrot regexin ja syötteen!

+ mikäli merkkijonon syöttämisen jälkeen ei tapahdu mitään, on kyseessä todennäköisesti ylivuototilanne. Mikäli samantyyppisestä viasta ei ole raportoitu aikaisemmin, avaathan uuden issuen, jossa kerrot regexin ja syötteen!
