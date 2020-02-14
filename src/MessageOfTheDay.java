import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class calls for and receives the message of the day from a given url. It
 * sends a getrequest to the messageOfTheDayURL and recieves a coded string from
 * the website. Then decodes the coded string. Then sends another getrequest to
 * the website using the decoded string and recieves the messageOfTheDay.
 * 
 * @author Hugo Green
 */

public class MessageOfTheDay {

	/**
	 * Returns the message of the day
	 * 
	 * @return messageOfTheDay The message of the day.
	 * @throws IOException if the GETrequest fails
	 */
	public static String GetMessageOfTheDay() throws IOException {
		String messageOfTheDayURL = "http://cswebcat.swan.ac.uk/puzzle";
		String solutionURL = "http://cswebcat.swan.ac.uk/message?solution=";

		// gets coded string
		String puzzleMessage = GETrequest(messageOfTheDayURL);

		// decodes string
		String decodedPuzzle = decodePuzzle(puzzleMessage);

		// gets MESSAGE OF THE DAY
		String messageOfTheDay = GETrequest((solutionURL + decodedPuzzle));

		return messageOfTheDay;
	}

	/**
	 * Sends a get request to a url and returns the response.
	 * 
	 * @param url The url a get request is being sent to.
	 * @return The response from the url get request.
	 * @throws IOException When reading from the url fails.
	 */
	private static String GETrequest(String url) throws IOException {
		// create url object for message of the day website
		URL websiteURL = new URL(url);

		// openconnection on url and set request to get
		HttpURLConnection connection = (HttpURLConnection) websiteURL.openConnection();
		connection.setRequestMethod("GET");

		// returns 200 if processed successfully
		int responseCode = connection.getResponseCode();
		// if responseCode is 200 then return the message
		if (responseCode == HttpURLConnection.HTTP_OK) {// Connection was a
														// success
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String GETresponse;
			GETresponse = in.readLine();
			in.close();
			return GETresponse;

		} else {
			System.out.println("GET request failed");
			return null;
		}
	}

	/**
	 * Shift all chars in the puzzle alternately 1 forward the 1 back through the
	 * alphabet and reloop through from Z to A and A to Z.
	 * 
	 * @param puzzle The String of characters that need to be decoded.
	 * @return The decoded puzzle string.
	 */
	private static String decodePuzzle(String puzzle) {
		String decoded = "";

		// iterate through each char in puzzle
		for (int i = 0; i < puzzle.length(); i++) {
			if ((i % 2) == 0) {
				// i is even, shift forward by 1
				if (puzzle.charAt(i) == 'Z') {
					decoded += 'A';
				} else {
					decoded += (char) (puzzle.charAt(i) + 1);
				}
			} else {
				// i is even, shift backward by 1
				if (puzzle.charAt(i) == 'A') {
					decoded += 'Z';
				} else {
					decoded += (char) (puzzle.charAt(i) - 1);
				}
			}
		}
		return decoded;
	}

}