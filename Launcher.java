package shimeji;

//WIP
import javax.swing.*;
import java.util.*;

public class Launcher extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Mascot> mascots = new ArrayList<Mascot>();

	public static void main(String[] args) 
	{
		Launcher launcher = new Launcher();
		launcher.init();
	}
	
	public void init()
	{
		JButton add = new JButton("Add another!");
		mascots.add(new Mascot());
		mascots.get(mascots.size()-1).init();
	}

}
