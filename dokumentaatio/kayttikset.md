# Käyttöohjeet


Regex-tulkkia käytetään testaamaan, kuuluuko jokin merkkijono annettuun säännölliseen lausekkeeseen (kieleen).

## Toiminta

Merkkijonoon voi kuulua mitä tahansa utf-8:n mukaisia merkkejä, kuten myös kieleen. Niinpä esimerkiksi säännöllinen lause 'a@?x'
vastaa kieltä {ax, a@x}. Käytettävissä on joukko erilaisia erikoismerkkejä:

#### ?
 * Merkki tarkoittaa "kerran tai ei ollenkaan". Siispä esimerkiksi lauseke 'aa?' tarkoittaa joko merkkijonoa 'a' tai 'aa', sillä toinen 'a' voi toistua joko kerran tai ei ollenkaan.

#### * 
 * Merkki tarkoittaa mielivaltaista määrää toistoja, myös nolla kertaa. Niinpä esimerkiksi regex 'a*' vastaa joukkoa {\\e, a, aa, aaa, aaaa, ...}, jossa \\e on tyhjä merkkijono.

#### \+
 * Merkki tarkoittaa yhtä tai useampaa toistoa. Tällöin 'a+' vastaa merkkijonojoukkoa {a, aa, aaa, aaaa, aaaaa, ...}.

#### ()
 * Sulkeet tarkoittavat ryhmää, ja toimivat kuin matematiikassa. Ryhmiä voidaan käsitellä kuin yksittäisiä merkkejä niin, että esimerkiksi 'aivo(kapasiteetti)?' vastaa merkkijonoja 'aivo' ja 'aivokapasiteetti'.

#### |
 * Merkin merkitys on "tai". Niinpä 'a(bb|ll)a' vastaa kahta merkkijonoa, 'abba' ja 'alla'.

#### .
 * Merkki tarkoittaa mitä tahansa merkkiä. Siispä esimerkiksi lausekkeen '.*' tulisi hyväksyä mikä tahansa merkkijono.

#### \\
 * Merkki tarkoittaa erikoismerkistä poistumista. Nyt siis 'a\\\*' tarkoittaa vain yhtä merkkijonoa, 'a\*', sillä '*' ei lasketa erikoismerkiksi. (Myös '\\\\' on '\\', eli merkki toimii itseensä)

#### \\e
 * Erikoispalautus \\e tarkoittaa merkkijonossa tyhjää. Tällöin a(\\e|bb)a vastaa kahta merkkijonoa, 'aa' tai 'abba'.

## Käyttöliittymä

Kun ohjelma käynnistetään, aukeaa käyttöliittymäikkuna:

| ![alku.png](https://raw.githubusercontent.com/LinAksel/tiralabra-regex/master/dokumentaatio/kuvat/alku.png) |
|:--:|
| *1. Säännöllisen lausekkeen kenttä* | *2. Merkkijonon kenttä*  | *3. Tämänhetkinen säännöllinen lauseke* | *4. Loki-ikkuna* |
