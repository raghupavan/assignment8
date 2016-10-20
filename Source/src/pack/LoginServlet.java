package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
		  
		         
		        // read form fields
		        String source = request.getParameter("username");
		        String destination = request.getParameter("password");
		         
		        System.out.println("username: " + source);
		        System.out.println("password: " + destination);
		        String latLongs[] = getLatLongPositions(source);
		        String latLongs1[] = getLatLongPositions(destination);
		        System.out.println("Latitude: "+latLongs[0]+" and Longitude: "+latLongs[1]);
			    System.out.println("Latitude: "+latLongs1[0]+" and Longitude: "+latLongs1[1]);
			    
			    Double d=distance(Double.parseDouble(latLongs[0]),Double.parseDouble(latLongs[1]),Double.parseDouble(latLongs1[0]),Double.parseDouble(latLongs1[1]),"k");
			    System.out.println("Distance between two points"+d);
			    
		         PrintWriter writer = response.getWriter();
		         response.setContentType("text/plain");
		         writer.println("Distance Between" +" "+source + " "+"and " +" "+destination+ " is" +" "+d +"  miles");
		         
		         int responseCode = 0;
		     	
		 		String api="http://api.openweathermap.org/data/2.5/weather?q="+source+"&APPID=68d31f0454905cbf6357da222b09baff&mode=json";
		 		String api1="http://api.openweathermap.org/data/2.5/weather?q="+destination+"&APPID=68d31f0454905cbf6357da222b09baff&mode=json";
		 		URL url = new URL(api);
		 		URL url1 = new URL(api1);
		 		String abd=url.toString();
		 		String abd1=url1.toString();
		 		String vc=callURL(abd);
		 		String vc1=callURL(abd1);
		 		System.out.println("Data"+vc);
		 		System.out.println("Data"+vc1);
		 		System.out.println("first check point");
		 		JSONObject jsonObject  = new JSONObject(vc);
		 		JSONObject jsonObject1  = new JSONObject(vc1);
		 		System.out.println("Second check point");
		 	System.out.println("JSON details"+jsonObject);
		 	JSONObject a=jsonObject.getJSONObject("main");
		 	System.out.println("befoe json1");
		 	JSONObject a1=jsonObject1.getJSONObject("main");
		 	System.out.println("After JSON");
		 	System.out.println("please get me"+a);
		 	System.out.println("please get me second"+a1);
		 	Integer b=(Integer) a.get("humidity");
		 	System.out.println("Between Humididty1");
		 	Integer b1=(Integer) a1.get("humidity");
		 	System.out.println("After humidity1");
		 	Double pre=(Double)a.get("pressure");
		 	System.out.println("Between pressure1");
		 	Double pre1=(Double) a1.get("pressure");
		 	System.out.println("After pressure 2");
		 	System.out.println("pressure at second position"+pre1);
		 	System.out.println("Before humidity second point");
		 	System.out.println("Humidity at second position"+b1);
		 	System.out.println("After Humidity second point");
		 	
		 	String source1=source.toUpperCase();
		 	String destination1=destination.toUpperCase();
		 	System.out.println("Humidity value"+b);
		 	System.out.println("Pressure value"+pre);
		 	writer.println("");
		 	writer.println(source1);
		    writer.println("Humidity value at starting point "+ " "+source+" "+b);
		     writer.println("Pressure value at starting point "+ " "+source+" "+pre);
		    writer.println("");
		    writer.println("");
		    System.out.println("in destination");
		  writer.println( destination1);
		   writer.println("Humidity value at Destincation point "+ " "+destination+" "+b1);
		    writer.println("Pressure value at Destination point "+ " "+destination+" "+pre1);
		 	//	
		    ;

		         
		
		doGet(request, response);
		}
		catch(Exception e)
		{
			System.out.println("errot in getting information");
		}
	}

	  public static Double distance(double lat1, double lon1, double lat2, double lon2,
			String sr) {
		  



	         double theta = lon1 - lon2;
	         double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	         dist = Math.acos(dist);
	         dist = rad2deg(dist);
	         dist = dist * 60 * 1.1515;
	         if (sr.equals("K")) {
	           dist = dist * 1.609344;
	         } else if (sr.equals("N")) {
	           dist = dist * 0.8684;
	           }
	         return (dist);
	       
	}
	  private static double rad2deg(double rad) {
			// TODO Auto-generated method stub
			 return (rad * 180.0 / Math.PI);
		}

		private static double deg2rad(double deg) {
			return (deg * Math.PI / 180.0);
		}

		public static String[] getLatLongPositions(String address) throws Exception
		  {
		    int responseCode = 0;
		    String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
		    URL url = new URL(api);
		    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
		    httpConnection.connect();
		    responseCode = httpConnection.getResponseCode();
		    if(responseCode == 200)
		    {
		      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		      Document document = builder.parse(httpConnection.getInputStream());
		      XPathFactory xPathfactory = XPathFactory.newInstance();
		      XPath xpath = xPathfactory.newXPath();
		      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
		      String status = (String)expr.evaluate(document, XPathConstants.STRING);
		      if(status.equals("OK"))
		      {
		         expr = xpath.compile("//geometry/location/lat");
		         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
		         expr = xpath.compile("//geometry/location/lng");
		         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
		         return new String[] {latitude, longitude};
		      }
		      else
		      {
		         throw new Exception("Error from the API - response status: "+status);
		      }
		    }
		    return null;
		  }
		
		public static String callURL(String myURL) {
			//System.out.println("Requeted URL:" + myURL);
			StringBuilder sb = new StringBuilder();
			URLConnection urlConn = null;
			InputStreamReader in = null;
			try {
				URL url = new URL(myURL);
				urlConn = url.openConnection();
				if (urlConn != null)
					urlConn.setReadTimeout(60 * 1000);
				if (urlConn != null && urlConn.getInputStream() != null) {
					in = new InputStreamReader(urlConn.getInputStream(),
							Charset.defaultCharset());
					BufferedReader bufferedReader = new BufferedReader(in);
					if(bufferedReader!=null)
					{
						int cp;
						while ((cp = bufferedReader.read()) != -1) {
							sb.append((char) cp);
						}
						bufferedReader.close();
					}
				}
			in.close();
			} catch (Exception e) {
				throw new RuntimeException("Exception while calling URL:"+ myURL, e);
			} 
	 
			return sb.toString();
					}
		
}
