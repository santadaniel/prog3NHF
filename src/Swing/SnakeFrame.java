package Swing;

import Game.LeaderBoard;

import javax.swing.*;
import java.io.IOException;

/**
 * The SnakeFrame class represents the main frame of the Snake game application.
 * It extends from JFrame.
 * It manages different states and their corresponding panels for display.
 */
public class SnakeFrame extends JFrame
{
    /**
     * The current state of the Snake game.
     */
    private State currentState;
    /**
     * The leaderboard for tracking player scores.
     */
    public LeaderBoard lb;

    /**
     * Constructs a new SnakeFrame with default settings.
     * Initializes the frame title, size, location, and creates a LeaderBoard instance.
     * Throws a runtime exception if there's an issue initializing the leaderboard.
     */
    public SnakeFrame()
    {
        setTitle("Snake");
        setVisible(true);
        setLocation(560, 140);
        // Adding to the height due to the Frame title bar.
        setSize(765, 765 + 23);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setCurrentState(State.Menu);
        try {
            lb = new LeaderBoard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

      /**
     * Updates the content pane based on the current state.
     * Called internally to switch between different panels.
     */
    private void updateContentPane()
    {
        switch (currentState)
        {
            case Menu:
                setContentPane(new MenuPanel(this));
                break;
            case OnePlayer, TwoPlayers:
                setContentPane(new GamePanel(this));
                break;
            case LeaderBoard:
                setContentPane(new LeaderBoardPanel(this));
                break;
        }
        revalidate();
        repaint();
    }

    /**
     * Sets the current state of the Snake game and updates the content pane accordingly.
     *
     * @param s The new state to set.
     */
    public void setCurrentState(State s)
    {
        currentState = s;
        updateContentPane();
    }

    /**
     * Gets the current state of the Snake game.
     *
     * @return The current state.
     */
    public State getCurrentState()
    {
        return currentState;
    }
}
