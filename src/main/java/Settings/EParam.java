package Settings;

/**
 * The ENUM representing all the settings.
 */
public enum EParam {
    // Game mechanics
    pacman_speed, pacman_starting_lives,
    ghost_speed, ghost_count, ghost_vuln_speed, ghost_vuln_val,
    food_distancing,
    large_food_score, small_food_score,
    special_food_spawn_odd,
    
    // Graphics
    line_color, line_thickness,
    background_color,
    path_width,
    
    label_color,
    label_size,
    
    // Controls
    KEY_turn_up, KEY_turn_down,
    KEY_turn_left, KEY_turn_right
}
