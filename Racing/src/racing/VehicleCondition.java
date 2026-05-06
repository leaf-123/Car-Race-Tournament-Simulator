/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racing;

/**
 * Represents the lifecycle condition of a vehicle during a race.
 *
 * A vehicle starts in the {@code OPTIMAL} state. As durability decreases,
 * it may become {@code WORN} or {@code CRITICAL}. If the vehicle has no fuel,
 * it becomes {@code CRITICAL} and needs a pit stop. If durability reaches zero,
 * it becomes {@code RETIRED} and can no longer continue the race.
 */

public enum VehicleCondition {
    OPTIMAL,  // fully effective
    WORN,  // moderate durability loss, reduced performance
    CRITICAL,  // heavily damaged, needs pit stop/repair
    RETIRED  // cannot continue the race
}
