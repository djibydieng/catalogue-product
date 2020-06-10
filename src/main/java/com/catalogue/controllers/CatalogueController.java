package com.catalogue.controllers;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.catalogue.entities.CompteClient;

import com.catalogue.entities.Panier;
import com.catalogue.entities.Produit;
import com.catalogue.exceptions.AuthenticationFailedException;
import com.catalogue.services.CompteClientService;
import com.catalogue.services.PanierService;
import com.catalogue.services.ProduitService;

@RestController
@RequestMapping("/catalogue-api")
public class CatalogueController {


	
	@Autowired
	private PanierService panierService;
	@Autowired
	private ProduitService produitService;
	@Autowired
	private CompteClientService compteClientService;
	
	/********************************  compte client *********************************/
	
	/* Methode permettant de creer un compte utilisateur
	 * 
	 */
	@PostMapping("/utilisateurs")
	public ResponseEntity<Void> createAccount(CompteClient compte) throws NoSuchAlgorithmException{
		this.compteClientService.ajouterUtilisateur(compte);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	/* Methode permettant de s'authentifier
	 * 
	 */
	@RequestMapping(value = "/utilisateurs/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<CompteClient> login(@RequestParam("username") String username, @RequestParam("password") String password) 
			throws NoSuchAlgorithmException, AuthenticationFailedException {
		CompteClient user = this.compteClientService.connecter(username, password);
		return new ResponseEntity<CompteClient> (user, HttpStatus.OK);
	}
	
	/*****************************************************************************/
	
	/********************* Panier *************************************************/
	
	/* Methode permettant de creer un panier 
	 * */
	@RequestMapping(value = "/paniers", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> createCart(@RequestParam("idClient") Long idClient) {
		Panier panier = new Panier();
		panier.setClient(this.compteClientService.listerClientParId(idClient));
		this.panierService.creerPanier(panier);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	/* Methode permettant d'ajouter un produit dans un panier
	 *  */
	@RequestMapping(value = "/paniers/{idPanier}/produits/{idProduit}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Void> addProduct(@PathVariable("idPanier") Long idPanier, @PathVariable("idProduit") Long idProduit,
			@RequestParam("quantity") Integer quantity) {
		this.panierService.ajouterProduit(idPanier, idProduit, quantity);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	/* Methode permettant de lister les produits d'un panier 
	 * */
	@RequestMapping(value="/paniers/{idPanier}/produits", method = RequestMethod.GET )
	public @ResponseBody ResponseEntity<List<Produit>> getAllProduitsByCart(@PathVariable("idPanier")Long idPanier){
		List<Produit> produits = this.panierService.listerProduitsPanier(idPanier);//produitDao.findAll();
		if(produits.isEmpty()) {
			return new ResponseEntity<>(produits,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(produits,HttpStatus.OK);
	}
	
	/* Methode permettant de supprimer un produit d'un panier 
	 * */
	@RequestMapping(value = "/paniers/{idPanier}/produits/{idProduit}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Void> deleteProductToCart(@PathVariable("idPanier") Long idPanier, @PathVariable("idProduit") Long idProduit) throws NotFoundException {
		this.panierService.supprimerProduit(idPanier, idProduit);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	/* Methode permettant de commander 
	 * 
	 */
	@RequestMapping(value="/commandes", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Void> order(@RequestParam("idPanier") Long idPanier){
		this.panierService.commander(idPanier);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	/*****************************************************************************************/
	
	/*********************** Produit ********************************************************/
	
	/* Methode permettant de lister les produits 
	 * */
	@RequestMapping(value="/produits", method = RequestMethod.GET )
	public ResponseEntity<List<Produit>> getAllProduits(){
		List<Produit> produits = this.produitService.listerProduits();//produitDao.findAll();
		if(produits.isEmpty()) {
			return new ResponseEntity<>(produits,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(produits,HttpStatus.OK);
	}
	
	/* Methode permettant d'ajouter un produit au catalogue
	 */
	@PostMapping("/produits")
	public ResponseEntity<Void> saveProduct(Produit produit){
		this.produitService.ajouterProduit(produit);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	/* Methode permettant de lister un produit par id
	 * 
	 */
	@RequestMapping(value="/produits/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produit> getProduitById(@PathVariable("id") Long id){
		Produit produit = this.produitService.listerProduitParId(id);//produitDao.getOne(id);
		if(produit == null) {
			return new ResponseEntity<>(null ,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(produit,HttpStatus.OK);
	}
	
}
