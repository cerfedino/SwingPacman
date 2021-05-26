package Settings;

/**
 * The ENUM representing all the settings.
 */
public enum EParam {
    // Game mechanics
    pacman_speed, pacman_starting_lives,
    ghost_speed, vulnerable_mstime, ghost_count,
    food_density,
    large_food_score, small_food_score,
    special_food_spawn_odd,
    
    // Graphics
    line_color, line_thickness,
    background_color,
    path_width,
    
    // Controls
    KEY_turn_up, KEY_turn_down,
    KEY_turn_left, KEY_turn_right
}
