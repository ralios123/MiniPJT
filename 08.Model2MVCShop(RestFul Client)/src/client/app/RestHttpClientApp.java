package client.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.User;



public class RestHttpClientApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// 주석을 하나씩 처리해가며 실습
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
	//	RestHttpClientApp.getUserTest_JsonSimple();
	 //   RestHttpClientApp.listUserTest_JsonSimple();
	//	RestHttpClientApp.updateUserTest_JsonSimple();

		
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.getUserTest_Codehaus();
//	    RestHttpClientApp.listUserTest_Codehaus();
//		RestHttpClientApp.updateUserTest_Codehaus();

	    
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.LoginTest_JsonSimple();
//		RestHttpClientApp.addUserTest_JsonSimple();
	RestHttpClientApp.updateUserTest_JsonSimple01();
		
		
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.LoginTest_Codehaus();		
//		RestHttpClientApp.addUserTest_Codehaus();
	//RestHttpClientApp.updateUserTest_Codehaus01();
	
	}
	





public static void updateUserTest_JsonSimple01() throws Exception{

	HttpClient httpClient = new DefaultHttpClient();
	
	String url= 	"http://127.0.0.1:8080/user/json/updateUser";
	
	HttpPost httpPost = new HttpPost(url);
	httpPost.setHeader("Accept", "application/json");
	httpPost.setHeader("Content-Type", "application/json");
	
	
	
	JSONObject json = new JSONObject();
	json.put("userId", "팽수");
	json.put("userName", "클라우드");
	json.put("phone", "010-111-11");
	json.put("addr", "평택시");
	json.put("email", "ses03@naver.com");
	
	
	
	System.out.println("json:"+json);
	
	HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

	httpPost.setEntity(httpEntity01);
	HttpResponse httpResponse = httpClient.execute(httpPost);
	
	//==> Response 확인
	System.out.println(httpResponse);
	System.out.println();
	
	HttpEntity httpEntity = httpResponse.getEntity();
	
	System.out.println("Entity:"+httpEntity);
	
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

	System.out.println("br:"+br);
	
	System.out.println("[ Server 에서 받은 Data 확인 ] ");
	String user = br.readLine();
	System.out.println("null:"+user);
	
	JSONObject jsonobj = (JSONObject)JSONValue.parse(user);
	System.out.println(jsonobj);
	
	}
	
	






public static void updateUserTest_JsonSimple() throws Exception {
	
	// HttpClient : Http Protocol 의 client 추상화 
	HttpClient httpClient = new DefaultHttpClient();
	
	String url= 	"http://127.0.0.1:8080/user/json/updateUser/object";
			
	// HttpGet : Http Protocol 의 GET 방식 Request
	HttpGet httpGet = new HttpGet(url);
	httpGet.setHeader("Accept", "application/json");
	httpGet.setHeader("Content-Type", "application/json");
	
	// HttpResponse : Http Protocol 응답 Message 추상화
	HttpResponse httpResponse = httpClient.execute(httpGet);
	
	//==> Response 확인
	System.out.println(httpResponse);
	System.out.println();

	//==> Response 중 entity(DATA) 확인
	HttpEntity httpEntity = httpResponse.getEntity();
	
	//==> InputStream 생성
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	
	System.out.println("[ Server 에서 받은 Data 확인 ] ");
	String serverData = br.readLine();
	System.out.println(serverData);
	
	//==> 내용읽기(JSON Value 확인)
	JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
	System.out.println(jsonobj);
}



public static void updateUserTest_Codehaus() throws Exception {


	// HttpClient : Http Protocol 의 client 추상화 
	HttpClient httpClient = new DefaultHttpClient();
	
	String url= 	"http://127.0.0.1:8080/user/json/updateUser/object";
			
	// HttpGet : Http Protocol 의 GET 방식 Request
	HttpGet httpGet = new HttpGet(url);
	httpGet.setHeader("Accept", "application/json");
	httpGet.setHeader("Content-Type", "application/json");
	
	// HttpResponse : Http Protocol 응답 Message 추상화
	HttpResponse httpResponse = httpClient.execute(httpGet);
	
	//==> Response 확인
	System.out.println(httpResponse);
	System.out.println();

	//==> Response 중 entity(DATA) 확인
	HttpEntity httpEntity = httpResponse.getEntity();
	
	//==> InputStream 생성
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	

	
	//==> 내용읽기(JSON Value 확인)
	JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
	System.out.println(jsonobj);
	
	ObjectMapper objectMapper = new ObjectMapper();
	 User user = objectMapper.readValue(jsonobj.toString(), User.class);
	 System.out.println(user);
		
		}

	
public static void listUserTest_JsonSimple() throws Exception{

	HttpClient httpClient = new DefaultHttpClient();
	
	String url = "http://127.0.0.1:8080/user/json/listUser/admin";
	
	HttpGet httpGet = new HttpGet(url);
	httpGet.setHeader("Accept", "application/json");
	httpGet.setHeader("Content-Type", "application/json");
	
	HttpResponse httpResponse = httpClient.execute(httpGet);
	
	//==> Response 확인
	System.out.println(httpResponse);
	System.out.println();
	
	HttpEntity httpEntity = httpResponse.getEntity();
	
	//==> InputStream 생성
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	
	System.out.println("[ Server 에서 받은 Data 확인 ] ");
	String serverData = br.readLine();
	System.out.println(serverData);
	
	//==> 내용읽기(JSON Value 확인)
	JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
	System.out.println(jsonobj);
}
	
	
public static void listUserTest_Codehaus() {
	
}










public static void addUserTest_JsonSimple() throws Exception {

	HttpClient httpClient = new DefaultHttpClient();
	
	String url = "http://127.0.0.1:8080/user/json/addUser";
	
	
	HttpPost httpPost = new HttpPost(url);
	httpPost.setHeader("Accept", "application/json");
	httpPost.setHeader("Content-Type", "application/json");
	
	
	JSONObject json = new JSONObject();
	json.put("userId", "백앤드");
	json.put("userName", "오라클");
	json.put("addr", "고양시");
	json.put("password", "5555");
	json.put("role", "user");
	
	
	HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

	httpPost.setEntity(httpEntity01);
	HttpResponse httpResponse = httpClient.execute(httpPost);
	
	//==> Response 확인
	System.out.println(httpResponse);
	System.out.println();
	
	HttpEntity httpEntity = httpResponse.getEntity();
	
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

	
	
	System.out.println("[ Server 에서 받은 Data 확인 ] ");
	String user = br.readLine();
	
	
	JSONObject jsonobj = (JSONObject)JSONValue.parse(user);
	System.out.println(jsonobj);
	
	}

	

	public static void addUserTest_Codehaus()throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/addUser";
	
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
				
		User user01 = new User();
		user01.setUserId("팽수");
		user01.setUserName("뽀로로");
		user01.setAddr("평양시");
		user01.setPassword("1234");
		user01.setRole("user");
		
		ObjectMapper objectMapper01 = new ObjectMapper();
		
		String jsonValue = objectMapper01.writeValueAsString(user01);
		HttpEntity httpEntity01 =  new StringEntity(jsonValue, "utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity httpEntity = httpResponse.getEntity();
		
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user);
}







	//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
	public static void getUserTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";
				
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
	public static void getUserTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
	
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}
//================================================================//	
	
//================================================================//
	//2.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
	public static void LoginTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ 방법 1 : String 사용]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ 방법 2 : JSONObject 사용]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
	}
	
	
	//2.2 Http Protocol POST 방식 Request : FromData전달 
	//==> JsonSimple + codehaus 3rd party lib 사용
	public static void LoginTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
//		//[ 방법 1 : String 사용]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ 방법 2 : JSONObject 사용]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//[ 방법 3 : codehaus 사용]
		User user01 =  new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(user01);
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}	
	
}