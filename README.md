Nella cartella Database_model è presente il modello Entità Relazione del DB e il file Searchouse.sql
da importare in una connessione locale se si volesse il DB in locale configurando opportunamente:
- DB_CONNECTION;
- DB_USER;
- DB_PASSWORD;
della classe AppUtil

Il DB in remoto utilizzato è RemoteMySQL e i valori di:
- DB_CONNECTION;
- DB_USER;
- DB_PASSWORD;
della classe AppUtil attualmente sono impostati con i dati per la connessione in remoto.

All'apertura il DB non conterrà annunci e nemmeno account registrati.
Occorre quindi registrarsi e inserire gli annunci.