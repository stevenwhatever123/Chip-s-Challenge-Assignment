import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class represents as the controller for InventoryScreen.fxml. It produce
 * a screen of inventory that the player possess.
 * 
 * @author Steven Ho.
 * @version 1.2
 */

public class InventoryScreenController {

	@FXML
	private ImageView redKey;

	@FXML
	private ImageView yellowKey;

	@FXML
	private ImageView greenKey;

	@FXML
	private ImageView dynamite;

	@FXML
	private ImageView flipper;

	@FXML
	private ImageView rubberBoot;

	@FXML
	private Label redKeyNum;

	@FXML
	private Label yellowKeyNum;

	@FXML
	private Label greenKeyNum;

	@FXML
	private Label dynamiteNum;

	@FXML
	private Label flipperNum;

	@FXML
	private Label rubberBootNum;

	/**
	 * Sets the number of red key that the player has.
	 * 
	 * @param i The number of red key.
	 */
	public void setRedKeyNum(int i) {
		String redKeyText = Integer.toString(i);
		this.redKeyNum.setText(redKeyText);
	}

	/**
	 * Sets the number of yellow key that the player has.
	 * 
	 * @param i The number of yellow key.
	 */
	public void setYellowKeyNum(int i) {
		String yellowKeyText = Integer.toString(i);
		this.yellowKeyNum.setText(yellowKeyText);
	}

	/**
	 * Sets the number of green key that the player has.
	 * 
	 * @param i The number of green key.
	 */
	public void setGreenKeyNum(int i) {
		String greenKeyText = Integer.toString(i);
		this.greenKeyNum.setText(greenKeyText);
	}

	/**
	 * Sets the number of dynamite that the player has.
	 * 
	 * @param i The number of dynamite.
	 */
	public void setDynamiteNum(int i) {
		String dynamiteText = Integer.toString(i);
		this.dynamiteNum.setText(dynamiteText);
	}

	/**
	 * This method sets if the player has flipper or not.
	 * 
	 * @param bool Whether the player has a flipper item.
	 */
	public void setFlipperNum(boolean bool) {
		if (bool) {
			this.flipperNum.setText("Collected");
		} else {
			this.flipperNum.setText("Not Collected");
		}
	}

	/**
	 * This method sets if the player has rubber boot or not.
	 * 
	 * @param bool If the player has rubber boot or not.
	 */
	public void setRubberBootNum(boolean bool) {
		if (bool) {
			this.rubberBootNum.setText("Collected");
		} else {
			this.rubberBootNum.setText("Not Collected");
		}
	}

	/**
	 * Initialize what is going to be displayed when the InventoryScreen fxml is
	 * being loaded.
	 * 
	 * @throws FileNotFoundException if image is not found.
	 * @throws IOException           If the label and imageview is empty.
	 */
	public void initialize() throws FileNotFoundException, IOException {
		ColouredKey redKey = new ColouredKey("Red");
		ColouredKey yellowKey = new ColouredKey("Yellow");
		ColouredKey greenKey = new ColouredKey("Green");
		Dynamite dynamite = new Dynamite();
		Flipper flipper = new Flipper();
		RubberBoot rubberBoot = new RubberBoot();
		this.redKey.setImage(redKey.getImageLink());
		this.yellowKey.setImage(yellowKey.getImageLink());
		this.greenKey.setImage(greenKey.getImageLink());
		this.dynamite.setImage(dynamite.getImageLink());
		this.flipper.setImage(flipper.getImageLink());
		this.rubberBoot.setImage(rubberBoot.getImageLink());

		this.redKeyNum.setAlignment(Pos.CENTER);
		this.redKeyNum.setFont(new Font(22));

		this.yellowKeyNum.setAlignment(Pos.CENTER);
		this.yellowKeyNum.setFont(new Font(22));

		this.greenKeyNum.setAlignment(Pos.CENTER);
		this.greenKeyNum.setFont(new Font(22));

		this.dynamiteNum.setAlignment(Pos.CENTER);
		this.dynamiteNum.setFont(new Font(22));

		this.flipperNum.setAlignment(Pos.CENTER);
		this.flipperNum.setFont(new Font(15));

		this.rubberBootNum.setAlignment(Pos.CENTER);
		this.rubberBootNum.setFont(new Font(15));
	}

}
