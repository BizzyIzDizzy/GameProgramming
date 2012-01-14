package info.bizzyizdizzy.graphics.lwjgl.samples.SpaceInvaiders;

/**
 * An entity representing a shot fired by the player's ship
 *
 * @author Kevin Glass
 * @author Brian Matzon
 */
public class ShotEntity extends Entity {

	/** Top border at which shots are outside */
	private static final int	TOP_BORDER	= -100;

	/** The vertical speed at which the players shot moves */
	private float							moveSpeed		= -300;

	/** The game in which this entity exists */
	private Game							game;

	/** True if this shot has been "used", i.e. its hit something */
	private boolean						used;

	/**
	 * Create a new shot from the player
	 *
	 * @param game The game in which the shot has been created
	 * @param sprite The sprite representing this shot
	 * @param x The initial x location of the shot
	 * @param y The initial y location of the shot
	 */
	public ShotEntity(Game game, String sprite, int x, int y) {
		super(game.getSprite(sprite), x, y);

		this.game = game;
		dy = moveSpeed;
	}

  /**
   * Reinitializes this entity, for reuse
   *
   * @param x new x coordinate
   * @param y new y coordinate
   */
	public void reinitialize(int x, int y) {
		this.x = x;
		this.y = y;
		used = false;
	}

	/**
	 * Request that this shot moved based on time elapsed
	 *
	 * @param delta The time that has elapsed since last move
	 */
	public void move(long delta) {
		// proceed with normal move
		super.move(delta);

		// if we shot off the screen, remove ourselfs
		if (y < TOP_BORDER) {
			game.removeEntity(this);
		}
	}

	/**
	 * Notification that this shot has collided with another
	 * entity
	 *
	 * @param other The other entity with which we've collided
	 */
	public void collidedWith(Entity other) {
		// prevents double kills, if we've already hit something,
		// don't collide
		if (used) {
			return;
		}

		// if we've hit an alien, kill it!
		if (other instanceof AlienEntity) {
			// remove the affected entities
			game.removeEntity(this);
			game.removeEntity(other);

			// notify the game that the alien has been killed
			game.notifyAlienKilled();
			used = true;
		}
	}
}