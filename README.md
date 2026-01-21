Compose

Compose on moderni tapa rakentaa käyttöliittymä Kotlin-funktioilla. Se perustuu dataan: UI on datan suora kuva. Kun sovelluksen tila (State) muuttuu, Compose piirtää automaattisesti muuttuneet osat näytöstä uudelleen (Recomposition).

ViewModel vs. Remember

Remember on sidottu yksittäiseen funktioon. Se on "lyhytkestoinen muisti": jos näyttö käännetään tai tuhotaan, remember-muuttujan tieto katoaa (esim. checkboxin valinta nollautuu).

ViewModel on sovelluksen "pitkäkestoinen muisti". Se elää käyttöliittymän ulkopuolella ja säilyttää datan (kuten tehtävälistan ja käyttäjän syötteet) turvassa, vaikka näyttö käännettäisiin tai luotaisiin uudelleen.
