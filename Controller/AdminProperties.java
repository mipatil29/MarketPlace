package Controller;

//Ryan: Always be sure to include useful comments in each file.
//getter setter for Admin Properties
public class AdminProperties {

	//Ryan: Should this be on the Client side or the Server side?
	/*I implemented Admin functionality using command pattern 
	  and I can not pass values to the  Controller through parameters, 
	  that's why I used getter setter on client side
	*/
	private String parentPrductCategory = null;
	private String product = null;
	private String itemName = null;
	private String price = null;
	private String quantity = null;
	private String UpdateType = null;
	private String prodDesc = null;
	
	public String getParentPrductCategory(){
		return parentPrductCategory;
	}
	public String getProduct(){
		return product;
	}
	public String getItemName(){
		return itemName;
	}
	public String getPrice(){
		return price;
	}
	public String getQuantity(){
		return quantity;
	}
	public String getUpdateType(){
		return UpdateType;
	}
	public String getProductDesc(){
		return prodDesc;
	}
	public void SetParentPrductCategory(String parntPrdctCatgry){
		parentPrductCategory = parntPrdctCatgry;
	}
	public void Setproduct(String prodct){
		product = prodct;
	}
	public void SetItemName(String itemNme){
		itemName =	itemNme;
	}
	public void SetPrice(String prce){
		price = prce;
	}
	public void SetQuantity(String qty){
		quantity = qty;
	}
	public void SetUpdateType(String updateType){
		UpdateType = updateType;
	}
	public void SetProdDesc(String proddesc){
		prodDesc = proddesc;
	}
}
