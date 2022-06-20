package com.exprivia.concessionario.API.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.exprivia.concessionario.API.dto.AutovetturaDTO;
import com.exprivia.concessionario.API.dto.ClienteDTO;
import com.exprivia.concessionario.API.dto.ConfigurazioneModelloDTO;
import com.exprivia.concessionario.API.dto.VenditaDTO;
import com.exprivia.concessionario.API.model.Autovettura;
import com.exprivia.concessionario.API.model.Cliente;
import com.exprivia.concessionario.API.model.Configurazione;
import com.exprivia.concessionario.API.model.ConfigurazioneModello;
import com.exprivia.concessionario.API.model.Costruttore;
import com.exprivia.concessionario.API.model.Modello;
import com.exprivia.concessionario.API.model.Vendita;
import com.exprivia.concessionario.API.services.CostruttoreServicesImplementation;
import com.exprivia.concessionario.API.services.ModelloServicesImplementation;
import com.exprivia.concessionario.API.services.VenditaServicesImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.exprivia.concessionario.API.services.AutovetturaServicesImplementation;
import com.exprivia.concessionario.API.services.ClientiServicesImplementation;
import com.exprivia.concessionario.API.services.ConfigurazioneModelloServicesImplementation;
import com.exprivia.concessionario.API.services.ConfigurazioneServicesImplementation;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ConcessionarioController {

	@Autowired
	private CostruttoreServicesImplementation costruttoreService;

	@Operation(summary = "Get dei costruttori EX: FORD")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Risultati Ottenuti",content = @Content(schema = @Schema(implementation = Costruttore.class))),             
	        @ApiResponse(responseCode = "404", description = "Configurazioni non trovate") })
	@GetMapping("/costruttore")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Costruttore>> getAllCostruttori() {
		return new ResponseEntity<>(costruttoreService.getAllCostruttori(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get Costruttore By Id")	
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Risultato Ottenuto"),             
	        @ApiResponse(responseCode = "404", description = "Costruttore by id non trovato",content = @Content(schema = @Schema(not = Costruttore.class))) })
	@GetMapping("/costruttore/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Costruttore> getCostruttoreByid(@PathVariable Integer id) {
		return new ResponseEntity<>(costruttoreService.getCostruttoreById(id), HttpStatus.OK);
	}
	
	@Operation(summary = "Aggiungi Costruttore",description = "Aggiunge un nuovo costruttore ovvero il marchio dell'auto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Costruttore inserito"),   
	        @ApiResponse(responseCode = "400", description = "Input non valido"), 
			@ApiResponse(responseCode = "409", description =  "Costruttore già presente", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/costruttore/post")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Costruttore> addCostruttore(@Parameter(description="Contact to add. Cannot null or empty.", 
            required=true, schema=@Schema(implementation = Costruttore.class))@Valid @RequestBody Costruttore costruttore) {
		return new ResponseEntity<>(costruttoreService.addCostruttori(costruttore), HttpStatus.CREATED);
	}

	@Operation(summary = "Update Costruttore",description = "Aggiorna il costruttore dell'auto ovvero il marchio appartenente all'autovettura,inserire l'id di riferimento nel path e il marchio nel body")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Update Completato"),
		        @ApiResponse(responseCode = "400", description = "Input non valido"),
		        @ApiResponse(responseCode = "404", description = "ID url non trovato"),
		        @ApiResponse(responseCode = "405", description = "Path errato", content = {
						@Content(mediaType = "application/json") }) })
	@PutMapping("/costruttore/put/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Costruttore> updateCostruttore(@Parameter(description="Id del costruttore da aggiornare. Non può essere vuoto.", 
            required=true)@PathVariable Integer id,
			@RequestBody Costruttore costruttore) {
		return new ResponseEntity<>(costruttoreService.updateCostruttori(id, costruttore), HttpStatus.OK);
	}

	/*
	 * @DeleteMapping("/costruttore/delete/{id}") public void
	 * deleteCostruttore(@PathVariable Integer id) {
	 * costruttoreService.deleteCostruttori(id); }
	 */

	// -------CONFIGURAZIONI CONTROLLER---------

	@Autowired
	private ConfigurazioneServicesImplementation configurazioneService;

	@Operation(summary = "Get delle configurazioni EX: SPORT EDITION")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Risultati Ottenuti"),             
	        @ApiResponse(responseCode = "404", description = "Configurazioni non trovate",content = @Content(schema = @Schema(not = Configurazione.class))) })
	@GetMapping("/getConfigurazioni")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Configurazione>> getAllConfigurazioni() {
		return new ResponseEntity<>(configurazioneService.getAllConfigurazioni(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get Configurazione By Id")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Risultato Ottenuto"),             
		        @ApiResponse(responseCode = "404", description = "Configurazione by id non trovata",content = @Content(schema = @Schema(not = Configurazione.class))) })
	@GetMapping("/getConfigurazione/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Configurazione> getConfigurazioneByid(@PathVariable Integer id) {
		return new ResponseEntity<>(configurazioneService.getConfigurazioneById(id), HttpStatus.OK);
	}
	
	@Operation(summary = "Aggiungi Configurazione",description = "Inserisce una nuova configurazione ovvero la versione dell'auto ex : sport line")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Configurazione inserita"),   
	        @ApiResponse(responseCode = "400", description = "Input non valido"), 
			@ApiResponse(responseCode = "409", description =  "Configurazione già presente", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/configurazione/{x_id_costruttore}/post")
	public ResponseEntity<Configurazione> addConfigurazione(
			@Parameter(description = "Chiave Esterna del costruttore ,inserire un valore di riferimento")@PathVariable(value = "x_id_costruttore") Integer x_id_costruttore,
			@Valid @RequestBody Configurazione configurazione) {
		configurazione.setCostruttore(costruttoreService.getCostruttoreById(x_id_costruttore));

		return new ResponseEntity<>(configurazioneService.addConfigurazione(configurazione), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Update Configurazione",description = "Aggiorna il nome della configurazione (versione dell'auto ex: sport line),inserire l'id di riferimento nel path e la descrizione nel body")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Update Completato"),
		        @ApiResponse(responseCode = "400", description = "Input non valido"),
		        @ApiResponse(responseCode = "404", description = "ID url non trovato"),
		        @ApiResponse(responseCode = "405", description = "Path errato", content = {
						@Content(mediaType = "application/json") }) })
	@PutMapping("/configurazione/put/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Configurazione> updateConfigurazione(@Parameter(description="Id della configurazione da aggiornare. Non può essere vuoto.", 
            required=true)@PathVariable Integer id,
			@RequestBody Configurazione configurazione) {
		return new ResponseEntity<>(configurazioneService.updateConfigurazione(id, configurazione), HttpStatus.OK);
	}

	// -------MODELLI CONTROLLER-------------

	@Autowired
	private ModelloServicesImplementation modelloService;
	
	@Operation(summary = "Get Modelli")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Risultati Ottenuti"),             
		        @ApiResponse(responseCode = "404", description = "Modelli non trovati",content = @Content(schema = @Schema(not = Modello.class))) })
	@GetMapping("/getModelli")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Modello>> getAllModelli() {
		return new ResponseEntity<>(modelloService.getAllModelli(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get Modello BY ID")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Risultato Ottenuto"),             
		        @ApiResponse(responseCode = "404", description = "Modello by id non trovato",content = @Content(schema = @Schema(not = Modello.class))) })
	@GetMapping("/getModello/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Modello> getModelloByid(@PathVariable Integer id) {
		return new ResponseEntity<>(modelloService.getModelloById(id), HttpStatus.OK);
	}
	
	@Operation(summary = "Post Modello")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Modello inserito"),   
	        @ApiResponse(responseCode = "400", description = "Input non valido"), 
			@ApiResponse(responseCode = "409", description =  "Modello già presente", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/modello/post")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Modello> addModello(@Valid @RequestBody Modello modello) {
		return new ResponseEntity<>(modelloService.addModello(modello), HttpStatus.CREATED);
	}

	@Operation(summary = "Update Modello",description = "Aggiorna il nome del modello dell'autovettura,inserire l'id di riferimento nel path e descrizione,annoProduzione nel body ")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Update Completato"),
		        @ApiResponse(responseCode = "400", description = "Input non valido"),
		        @ApiResponse(responseCode = "404", description = "ID url non trovato"),
		        @ApiResponse(responseCode = "405", description = "Path errato", content = {
						@Content(mediaType = "application/json") }) })
	@PutMapping("/modello/put/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Modello> updateModello(@Parameter(description="Id del modello da aggiornare. Non può essere vuoto.", 
            required=true)@PathVariable Integer id, @RequestBody Modello modello) {
		return new ResponseEntity<>(modelloService.updateModello(id, modello), HttpStatus.OK);
	}

	// -------Configurazioni_Modelli CONTROLLER------------

	@Autowired
	private ConfigurazioneModelloServicesImplementation configurazioneModelloService;

	@Operation(summary = "Update Configurazione/Modello ",description = "Aggiorna la tabelle inerente le configurazioni/modello,ovvere le caratteristiche dell'auto presente e NON. Inserire l'id di riferimento nel path ")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Update Completato"),
		        @ApiResponse(responseCode = "400", description = "Input non valido"),
		        @ApiResponse(responseCode = "404", description = "ID url non trovato"),
		        @ApiResponse(responseCode = "405", description = "Path errato", content = {
						@Content(mediaType = "application/json") }) })
	@PutMapping("/configurazioneModello/put/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<ConfigurazioneModello> updateconfigurazioneModello(@Parameter(description="Id della Configurazine/Modello da aggiornare. Non può essere vuoto.", 
            required=true)@PathVariable Integer id,
			@RequestBody ConfigurazioneModello configurazioneModello) {
		return new ResponseEntity<>(configurazioneModelloService.updateConfigurazioneModello(id, configurazioneModello),
				HttpStatus.OK);
	}

	// -----AUTOVETTURA CONTROLLER-----

	@Autowired
	public AutovetturaServicesImplementation autovetturaService;

	@Operation(summary = "Get Autovetture")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Risultati Ottenuti"),             
		        @ApiResponse(responseCode = "404", description = "Auto non trovate",content = @Content(schema = @Schema(not = Autovettura.class))) })
	@GetMapping("/getAutovetture")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Autovettura>> getAllAutovettura() {

		return new ResponseEntity<>(autovetturaService.getAllAutovetture(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get Autovettura BY ID")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Risultato Ottenuto"),             
		        @ApiResponse(responseCode = "404", description = "Auto by id non trovata",content = @Content(schema = @Schema(not = Autovettura.class))) })
	@GetMapping("/getAutovettura/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Autovettura> getAutuvetturaByid(@PathVariable String id) {

		return new ResponseEntity<>(autovetturaService.getAutovetturaById(id), HttpStatus.OK);
	}
	
	@Operation(summary = "Post Configurazione/Modello",description = "Aggiunge una nuova autovettura definendo nel path l'id della configurazione esistente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Autovettura inserita"),   
	        @ApiResponse(responseCode = "400", description = "Input non valido"), 
			@ApiResponse(responseCode = "409", description =  "Autovettura già presente", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/autovettura/{x_id_ConfigurazioneModello}/post")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Autovettura> addAutovettura(
			@Parameter(description = "Chiave Esterna della configurazione/modello,inserire un valore di riferimento", 
		            required=true)@PathVariable(value = "x_id_ConfigurazioneModello") Integer x_id_ConfigurazioneModello,
			@Valid @RequestBody Autovettura autovettura) {
		autovettura.setConfigurazioniModelli(
				configurazioneModelloService.getConfigurazioneModelloById(x_id_ConfigurazioneModello));
		return new ResponseEntity<>(autovetturaService.addAutovettura(autovettura), HttpStatus.CREATED);
	}

	// -----CLIENTE CONTROLLER-------

	@Autowired
	private ClientiServicesImplementation clientiService;

	@Operation(summary = "Get clienti che hanno acquistato un veicolo")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Risultato Ottenuto"),             
		        @ApiResponse(responseCode = "404", description = "Clienti non trovati",content = @Content(schema = @Schema(not = Cliente.class))) })
	@GetMapping("/cliente")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Cliente>> getAllClienti() {

		return new ResponseEntity<>(clientiService.getAllClienti(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get Cliente BY ID")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Risultato Ottenuto"),             
		        @ApiResponse(responseCode = "404", description = "Cliente non trovato",content = @Content(schema = @Schema(not = Cliente.class))) })
	@GetMapping("/cliente/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Cliente> getClienteByid(@PathVariable String id) {

		return new ResponseEntity<>(clientiService.getClienteByid(id), HttpStatus.OK);
	}

	// ----VENDITA CONTROLLER----

	@Autowired
	private VenditaServicesImplementation venditaService;
	
/*	@Operation(summary = "Get Vendita By ID")
	@GetMapping("/vendita/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Vendita> getVenditaByid(@PathVariable Integer id) {

		return new ResponseEntity<>(venditaService.getVenditeByid(id), HttpStatus.OK);
	}*/

	@Operation(summary = "Aggiunge una vendita con cliente esistente",description = "Se il cliente ha gia acquistato un autovettura in precedenza con questo metodo basta inserire soltando il codice fiscale per riferimento al cliente")
			@ApiResponses(value = {
					@ApiResponse(responseCode = "201", description = "Vendita inserita"),   
			        @ApiResponse(responseCode = "400", description = "Input non valido"), 
					@ApiResponse(responseCode = "409", description =  "Vendita già presente", content = {
					@Content(mediaType = "application/json") }) })
	@PostMapping("/vendita/{x_ntelaio}/{x_codice_fiscale}/post")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Vendita> addVendita(@Parameter(description="Chiave Esterna del telaio in riferimento all'autovettura")@PathVariable(value = "x_ntelaio") String x_ntelaio,
			@Parameter(description="Chiave Esterna del Codice Fiscale in riferimento al cliente")@PathVariable(value = "x_codice_fiscale") String x_codice_fiscale, @Valid @RequestBody Vendita vendita) {
		vendita.setAutovettura(autovetturaService.getAutovetturaById(x_ntelaio));
		vendita.setCliente(clientiService.getClienteByid(x_codice_fiscale));

		return new ResponseEntity<>(venditaService.addVendita(vendita), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Update Vendita",description = "Aggiornamento della vendita,è possibile aggiornare inserendo nel path l'id di riferimento e nel body i campi : data,prezzo,targa ")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Update Completato"),
		        @ApiResponse(responseCode = "400", description = "Input non valido"),
		        @ApiResponse(responseCode = "404", description = "ID url non trovato"),
		        @ApiResponse(responseCode = "405", description = "Path errato", content = {
						@Content(mediaType = "application/json") }) })
	@PutMapping("/vendita/put/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Vendita> updateVendita(@Parameter(description="Id della vendita da aggiornare. Non può essere vuoto.", 
            required=true)@PathVariable Integer id, @RequestBody Vendita vendita) {

		return new ResponseEntity<>(venditaService.updateVendite(id, vendita), HttpStatus.OK);
	}

	// ------CUSTOM CONTROLLER (DTO)-----

	@Operation(summary = "Get delle vendite con i relativi clienti ")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Risultati Ottenuto"),             
		        @ApiResponse(responseCode = "404", description = "Vendite non trovate",content = @Content(schema = @Schema(not = VenditaDTO.class))) })
	@GetMapping("/getVenditeClienti")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<VenditaDTO>> getAllVenditeClienti() {
		return new ResponseEntity<>(venditaService.findAllVendite(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get vendita con cliente per id ",description = "Get della vendita con relativo cliente indicando il riferimento dell'id")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Risultato Ottenuto"),             
		        @ApiResponse(responseCode = "404", description = "Vendita non trovata",content = @Content(schema = @Schema(not = VenditaDTO.class))) })
	@GetMapping("/getVenditeClienti/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<VenditaDTO> getAllVenditeClientiByid(@PathVariable Integer id) {
		return new ResponseEntity<>(venditaService.findVenditaById(id), HttpStatus.OK);
	}

	@Operation(summary = "Get delle auto presenti nel concessionario",description = "Trova le auto presenti nel concessionario disponibile per la vendita")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Risultati Ottenuti"),             
		        @ApiResponse(responseCode = "404", description = "Auto non trovate",content = @Content(schema = @Schema(not = ConfigurazioneModelloDTO.class))) })
	@GetMapping("/getAllCarInside")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<ConfigurazioneModelloDTO>> getAllCarInside() {
		return new ResponseEntity<>(configurazioneModelloService.findAllCarInside(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get di tutte le configurazioni_modelli",description = "Trova tutte le auto istanziate e non,ovvero la sola configurazione_modello nel concessionario per poterne generare una nuova dalle configurazioni esistenti  ")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Risultati Ottenuti"),             
		        @ApiResponse(responseCode = "404", description = "Risultati non trovati",content = @Content(schema = @Schema(not = ConfigurazioneModelloDTO.class))) })
	@GetMapping("/getAllCar")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<ConfigurazioneModelloDTO>> getAllConfigurazioniModelli() {
		return new ResponseEntity<>(configurazioneModelloService.findAllCar(), HttpStatus.OK);
	}

	
	@Operation(summary = "Get auto presente nel concessionario",description = "Trova l'auto per id presente nel concessionario disponibile per la vendita")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Risultato Ottenuto"),             
		        @ApiResponse(responseCode = "404", description = "Auto non trovata",content = @Content(schema = @Schema(not = ConfigurazioneModelloDTO.class))) })
	@GetMapping("/getAllCarInside/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<ConfigurazioneModelloDTO> getconfigurazioneModelloByid(@PathVariable Integer id) {
		return new ResponseEntity<>(configurazioneModelloService.findAllCarInsideById(id), HttpStatus.OK);
	}

/*	@Operation(summary = "Get autovettura con filtro")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Trova le caratteristiche dell'auto filtrando per n_telaio", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("/getAutoByTelaio/{n_telaio}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<ConfigurazioneModelloDTO> findCarByTelaio(@PathVariable String n_telaio) {
		return new ResponseEntity<>(configurazioneModelloService.findCarByTelaio(n_telaio), HttpStatus.OK);
	}*/

	/*
	 * @PutMapping("/configurazioneModello/updateQta/{id}") public
	 * ConfigurazioneModelloDTO updateQtaconfigurazioneModello(@PathVariable Integer
	 * id,
	 * 
	 * @RequestBody ConfigurazioneModelloDTO configurazioneModelloDTO) { return
	 * configurazioneModelloService.updateQtaConfigurazioneModello(id,
	 * configurazioneModelloDTO); } controller per l update del singolo campo
	 */

	@Operation(summary = "Aggiunge una vendita con nuovo cliente",description = "Aggiunge una vendita inserendo anche i campi relativi al nuovo cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Vendita inserita"),   
	        @ApiResponse(responseCode = "400", description = "Input non valido"), 
			@ApiResponse(responseCode = "409", description =  "Vendita con relativo cliente già presente", content = {
					@Content(mediaType = "application/json") }) })
	@PostMapping("/addVenditaCliente/post")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<VenditaDTO> addVendita(@Valid @RequestBody VenditaDTO venditeDTO) {
		Cliente cliente = new Cliente(venditeDTO.getCodiceFiscale(), venditeDTO.getNome(), venditeDTO.getCognome());
		clientiService.addCliente(cliente);
		return new ResponseEntity<>(venditaService.addVenditaCliente(venditeDTO), HttpStatus.CREATED);

	}

	@Operation(summary = "Aggiunge una nuova configurazione/modello",description = "Aggiunge una nuova configurazione/modello prendendento in input la fk di modelli e configurazione. Successivamente è possibile chiamare la Post di Autovettura per completare l'inserimento dell'auto nel concessionario")
	 @ApiResponses(value = { 
		        @ApiResponse(responseCode = "201", description = "Configurazione/Modello creata"),   
		        @ApiResponse(responseCode = "400", description = "Input non valido"), 
		        @ApiResponse(responseCode = "409", description = "Configurazione/Modello già esistente", content = {
						@Content(mediaType = "application/json") }) })	
	@PostMapping("/addConfigurazioneModello/post")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<ConfigurazioneModelloDTO> addConfigurazioneModello( @Parameter(description="Configurazione/Modello", 
            required=true, schema=@Schema(implementation = ConfigurazioneModelloDTO.class))
			@Valid @RequestBody ConfigurazioneModelloDTO configurazioneModelloDTO) {
		return new ResponseEntity<>(configurazioneModelloService.addConfigurazioneModello(configurazioneModelloDTO),
				HttpStatus.CREATED);
	}

	/*
	 * @PostMapping("/addConfigurazioneModello/post") public
	 * ConfigurazioneModelloDTO addConfigurazioneModello(
	 * 
	 * @RequestBody ConfigurazioneModelloDTO configurazioneModelloDTO) { return
	 * configurazioneModelloService.addConfigurazioneModello(
	 * configurazioneModelloDTO); }
	 */
	@Operation(summary = "Update Autovettura",description = "Aggiornamento con json custom  posso passare entrambi i valori o uno alla volta ")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Update Completato"),
		        @ApiResponse(responseCode = "400", description = "Input non valido"),
		        @ApiResponse(responseCode = "404", description = "ID url non trovato"),
		        @ApiResponse(responseCode = "405", description = "Path errato", content = {
				@Content(mediaType = "application/json") }) })	
	@PutMapping("/autovettura/put/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<AutovetturaDTO> updateAutovettura(@Parameter(description="Id dell'autovettura da aggiornare. Non può essere vuoto.", 
            required=true)@PathVariable(value = "id") String id,
			@RequestBody AutovetturaDTO autovetturaDTO) throws Exception {
		return new ResponseEntity<>(autovetturaService.updateAutovettura(id, autovetturaDTO), HttpStatus.OK);
	}
	
	@Operation(summary = "Update Cliente",description = "Update delle informazioni relative al cliente")
	 @ApiResponses(value = {
		        @ApiResponse(responseCode = "200", description = "Update Completato"),
		        @ApiResponse(responseCode = "400", description = "Input non valido"),
		        @ApiResponse(responseCode = "404", description = "ID url non trovato"),
		        @ApiResponse(responseCode = "405", description = "Path errato", content = {
				@Content(mediaType = "application/json") }) })
	@PutMapping("/cliente/put/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<ClienteDTO> updateCliente(@Parameter(description="Id del cliente aggiornare. Non può essere vuoto.", 
            required=true)@PathVariable(value = "id") String id,
			@RequestBody ClienteDTO clienteDTO) throws Exception {

		return new ResponseEntity<>(clientiService.updateCliente(id, clienteDTO), HttpStatus.OK);
	}

}
