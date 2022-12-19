package com.RCB.TestVagrant.Test;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.RCB.TestVagrant.Base.Base;
import com.RCB.TestVagrant.Payload.Payload;
import com.RCB.TestVagrant.Reusable.CommonUtils;

import io.restassured.path.json.JsonPath;

public class RCBJsonValidation extends Base{
	
	JsonPath rcbTeamData;
	
	@Test(priority=1,description="Validate that the team has only four foreign players and atleast one wicket keepr")
	public void foreignPlayerValidation() {
		
		/*
		 * If you want to test this code them comment the code from line no 25 to 28 and uncomment the line no 29
		 */
		
		 String teamRCBDataResponse = given().spec(requestSpecification())
				.when().get("end point")
				.then().spec(responseSpecification()).extract().asString();		
		 rcbTeamData = CommonUtils.rawToJson(teamRCBDataResponse);
//		 rcbTeamData = CommonUtils.rawToJson(Payload.rcbTeamData());
		 
		 int count = 0;
		int playerSize = rcbTeamData.getInt("player.size()");
		
		//First validation point "Validate that the team has only four foreign players"
		for(int idx=0;idx!=playerSize;idx++) {
			if(!rcbTeamData.getString("player["+idx+"].country").equals("India")) {
				count++;}
		}
		System.out.println("Foreign players count is :"+count);
		Assert.assertEquals(count, 4);
		
		//Second validation point "Validates that there is one wicket keeper"
		String role="";
		String playerName="";
		for(int indx=0;indx!=playerSize;indx++) {
			if(rcbTeamData.getString("player["+indx+"].role").contains("Wicket")) {
				role = rcbTeamData.getString("player["+indx+"].role");
				playerName = rcbTeamData.getString("player["+indx+"].name");
				break;
			}
		}
		System.out.println("Rcb "+role+" name is "+playerName);
		Assert.assertEquals(role, "Wicket-keeper");
	}

}
