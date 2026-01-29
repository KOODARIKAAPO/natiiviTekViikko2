Compose

Compose on moderni tapa rakentaa käyttöliittymä Kotlin-funktioilla. Se perustuu dataan: UI on datan suora kuva. Kun sovelluksen tila (State) muuttuu, Compose piirtää automaattisesti muuttuneet osat näytöstä uudelleen (Recomposition).

---

ViewModel vs. Remember

Remember on sidottu yksittäiseen funktioon. Se on "lyhytkestoinen muisti": jos näyttö käännetään tai tuhotaan, remember-muuttujan tieto katoaa (esim. checkboxin valinta nollautuu).

ViewModel on sovelluksen "pitkäkestoinen muisti". Se elää käyttöliittymän ulkopuolella ja säilyttää datan (kuten tehtävälistan ja käyttäjän syötteet) turvassa, vaikka näyttö käännettäisiin tai luotaisiin uudelleen.

---

MVVM Model-View-ViewModel

On sovlluksen 'arkkitehtuurimalli' jossa sovellus jaotellaan kolmeen eri osaan 
1. Model, sovelluksen data. Tässä sovelluksessa Task-data luokan ja enum-priorityn
2. View on sovelluksen UI näkymä. Se ei itsessään tee mitään muuta kun näyttää dataa.
3. View model, joka hallitsee sovelluksen tilaa ja logiikkaa. Tässä sovelluksessa funktiot, addTask, toggleDone, removeTask ja uutena lisäyksenä updateTask.
Mvvm tuo useita hyötyjä composesovelluksen ylläpitoon ja testaukseen. Koodi on selkeää, sillä se on osissa eri paketeissa sekä sitä on helppo ylläpitää.

---

StateFlow

StatFlow on tilanhallinta työkalu joka pitää muistissa viimeisimmän tilan, ja lähettää sen sovelluksessa eteenpäin. Kun tila muuttuu, StateFlow emitoi uuden arvon, ja kaikki sitä kuuntelevat komponentit. Se mahdollistaa single source of truth -periaatteen. Vain se tietää mikä pitää paikkansa sovelluksessa

StateFlow välittää sovelluksen tilaa (dataa), esimerkiksi tehtävälistan ViewModelista käyttöliittymään. ViewModelin funktiot (esim. addTask, toggleDone, removeTask) päivittävät StateFlow’n arvoa, jolloin uusi tila lähetetään eteenpäin. (Charlie)

Compose kuuntelee StateFlowta collectAsState()-funktion kautta. Eli kun StateFlowntila muuttuu compose päivittyy. Sitä voi miettiä vaikka tarjoiliana ravintolassa. StateFlow on tarjoilia, UI on ravintolan asiakas ja viewmodel, data, funktiot on keittiö. Paitsi että jos tarjoilia voisi päivittää annoksia realiajassa kun keittiö tekee uuden annoksen.

Monimutkasta





