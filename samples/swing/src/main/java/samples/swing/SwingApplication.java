package samples.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import org.springframework.web.client.RestTemplate;

@SuppressWarnings("serial")
public class SwingApplication extends JFrame {

	private ExecutorService threadPool = Executors.newFixedThreadPool(10);

	private JList<Actor> actorList = new JList<>();
	private JButton reloadButton = new JButton("Reload");
	private JLabel errorLabel = new JLabel("OK");

	private void runInEdt() {
		actorList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> new JLabel(
				"[" + value.id + "] " + value.firstName + " " + value.lastName.toUpperCase()));
		reloadButton.addActionListener(this::onReloadClick);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());

		getContentPane().add(reloadButton);
		getContentPane().add(errorLabel);
		getContentPane().add(actorList);

		setSize(new Dimension(1000, 400));
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void onReloadClick(ActionEvent event) {
		threadPool.submit(() -> {
			RestTemplate tem = new RestTemplate();
			try {
				Actor[] actors = tem.getForEntity("http://localhost:8080/list_users", Actor[].class)
						.getBody();
				displayActors(actors);
			} catch (Exception e) {
				displayError(stacktraceToString(e));
			}
		});
	}

	private void displayActors(Actor[] actors) {
		SwingUtilities.invokeLater(() -> {
			errorLabel.setText("OK");
			actorList.setListData(actors);
		});
	}

	private void displayError(String message) {
		SwingUtilities
				.invokeLater(() -> errorLabel.setText("<html>" + message.replace("\n", "<br/>") + "</html>"));
	}

	private static String stacktraceToString(Exception e) {
		try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw);) {
			e.printStackTrace(pw);
			return sw.toString();
		} catch (IOException e1) {
			return e1.getMessage();
		}
	}

	private void init() {
		SwingUtilities.invokeLater(this::runInEdt);
	}

	private SwingApplication() {
		init();
	}

	public static void main(String... args) {
		new SwingApplication();
	}
}
