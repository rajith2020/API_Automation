package general;

import static io.restassured.RestAssured.given;
import general.card_draw_reusable;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.codehaus.groovy.transform.EqualsAndHashCodeASTTransformation;
import org.junit.Assert;

public class card_draw_reusable {
 
	String url;
	public static Response draw_response;
	public static Response response;
	public static Response response1;
	private static int count;
	private static int result;
	 
	public static String new_deckid() ///will get new_deckid id for the newly 
	{
		return  response1.jsonPath().getString("deck_id").trim();
	}
	public static int new_remaining()//Will get remaining number from the response for the newly created card
	{
		try{
		 	result= Integer.parseInt(response1.jsonPath().getString("remaining").trim());
		}
		catch(Exception e){
			System.out.println(e);
			
		}
		return result;
	}
	public static String card_remaining() ///Will get remaining number from the response for the drawn created card
	{
		System.out.println("the number of card avaliable: "+ draw_response.jsonPath().getString("remaining").trim());
		 return   draw_response.jsonPath().getString("remaining").trim();
	}
	public static String error()//Will get the error message out of card
	{
		return   draw_response.jsonPath().getString("error").trim();
	}
	public static   int remaining_count(){
		String cardsremaining = response1.jsonPath().getString("remaining");
		System.out.println(cardsremaining);
		return Integer.parseInt(cardsremaining);
	}
	public static Response draw_cards(String path){
		 draw_response=given().contentType(ContentType.JSON).log().all().get(path);
		 return draw_response;
	}
	
	public static Response draw_number(int drawnumber){
		String path="https://deckofcardsapi.com/api/deck/"+new_deckid()+"/draw/?count="+drawnumber;
		 System.out.println("The end point "+path);
		  draw_response= given().contentType(ContentType.JSON).log().all().get(path);
		return draw_response; 
	}
	public static Response newcard(String url){
	 
		 return response = given().when().get(url);
		 	 
	}
	public static Response Drawnewcard(String url){
		 response1 = given().when().get(url);
		 
		 return response1;
		 	 
	}
	public static int upandrunning(){
		 
		return response.getStatusCode();
	}
 public static  void Result_Compare(int  Actau,int expect){
	 Assert.assertEquals( expect,Actau);
	
 }
 public static int get_DrawcardStatus(){
		return response1.getStatusCode();
	}
 public static int get_drawn_respcode(){
		return draw_response.getStatusCode();
	}
 
 public static int validate_count(String type1){
	  
	 try{			//will check if new card or old card 
		
		if (type1.equals("New")){
			 count = new_remaining(); 
		}
		else    {			 
			count = Integer.parseInt(card_remaining());
			
		}
		
		 
		}
		catch(Exception e){
			System.out.println(e);
		}
	return count; 	
 }
}
