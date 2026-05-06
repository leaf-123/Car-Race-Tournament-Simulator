/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racing;

/**
 * Represents the weather conditions on a race track.
 *
 * Weather affects the racing environment by influencing vehicle speed,
 * fuel consumption, and durability loss. Different weather types introduce
 * different levels of difficulty for drivers.
 *
 * CLEAR provides normal conditions with no performance penalties.
 * RAIN reduces speed and increases fuel consumption and durability loss.
 * FOG significantly reduces speed and may require a pit stop.
 */

public enum Weather {
    CLEAR,  // normal condition, no negative effects on performance
    RAIN,  // slippery condition, reduce speed, increase wear & fuel usage
    FOG  // low-visibility condition, reduce speed, may require pit stops
}
