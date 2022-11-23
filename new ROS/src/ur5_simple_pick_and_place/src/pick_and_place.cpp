#include <moveit/move_group_interface/move_group_interface.h>
#include <moveit/planning_scene_interface/planning_scene_interface.h>


int main(int argc, char** argv)
{
  ros::init(argc, argv, "move_group_interface_tutorial");
  ros::NodeHandle n;
  
  // ROS spinning must be running for the MoveGroupInterface to get information
  // about the robot's state. One way to do this is to start an AsyncSpinner
  // beforehand.
  ros::AsyncSpinner spinner(1);
  spinner.start();

    // MoveIt operates on sets of joints called "planning groups" and stores them in an object called
  // the `JointModelGroup`. Throughout MoveIt the terms "planning group" and "joint model group"
  // are used interchangably.
    // we need two move_group interfaces one for the arm and one for the gripper, coz we will be planning for two things, arm and gripper.
    static const std::string PLANNING_GROUP_ARM = "ur5_arm"; // name of the planning group for arm or the name of the MoveGroupInterface for arm
    static const std::string PLANNING_GROUP_GRIPPER = "gripper"; // name of the planning group for end effector or the name of the MoveGroupInterface for gripper
    
    // The :planning_interface:`MoveGroupInterface` class can be easily
    // setup using just the name of the planning group you would like to control and plan for.
    moveit::planning_interface::MoveGroupInterface move_group_interface_arm(PLANNING_GROUP_ARM);
    moveit::planning_interface::MoveGroupInterface move_group_interface_gripper(PLANNING_GROUP_GRIPPER); // in the above three lines we are instantiating the two MoveGroupInterfaces using the corresponding planning groups.

    // We can get a list of all the groups in the robot:
    ROS_INFO_NAMED("tutorial", "Available Planning Groups:");
    std::copy(move_group_interface_arm.getJointModelGroupNames().begin(),
            move_group_interface_arm.getJointModelGroupNames().end(), std::ostream_iterator<std::string>(std::cout, ", ")); // the above three lines are used to print the availaible planning groups

    moveit::planning_interface::MoveGroupInterface::Plan my_plan_arm; // we use it later for storing the motion plan created by the motion planner.
    
    // 1. Move to home position
    move_group_interface_arm.setJointValueTarget(move_group_interface_arm.getNamedTargetValues("home")); // the home position is defined as a set of joint position values inside the srdf file. We get these values by calling move_group_interface_arm.getNamedTargetValues("home"). Then we hand these values over to the move_group by calling setJointValueTarget.
    
    bool success = (move_group_interface_arm.plan(my_plan_arm) == moveit::planning_interface::MoveItErrorCode::SUCCESS); // using move_group_interface_arm.plan(my_plan_arm) we are calling the motion planner to store the motion plan in the my_plan_arm. We also check if the motion plan was succesfully created or not.

    ROS_INFO_NAMED("tutorial", "Visualizing plan 1 (pose goal) %s", success ? "" : "FAILED");

    move_group_interface_arm.move(); // to exceute the created motion plan, we need to call the move() method for the move group interface which is going to execute the motion. 

    // 2. Place the TCP (Tool Center Point, the tip of the robot) above the blue box
    geometry_msgs::PoseStamped current_pose; // we create a current_pose variable.
    current_pose = move_group_interface_arm.getCurrentPose("ee_link"); // we get the current pose of the end effector link, and store it in the current_pose variable.

    geometry_msgs::Pose target_pose1; // we define the target_pose1 variable.
  
    target_pose1.orientation = current_pose.pose.orientation; // we assign the current orientation to be the target orientation.
    target_pose1.position.x = 0.3;
    target_pose1.position.y = 0.5;
    target_pose1.position.z = 0.2;
    // we set the x, y and z variables to the correct location of the blue box.
    move_group_interface_arm.setPoseTarget(target_pose1); // now we set the pose to the interface
    // the below code ka explanation is same as the above one
    success = (move_group_interface_arm.plan(my_plan_arm) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

    ROS_INFO_NAMED("tutorial", "Visualizing plan 1 (pose goal) %s", success ? "" : "FAILED");

    move_group_interface_arm.move();


    moveit::planning_interface::MoveGroupInterface::Plan my_plan_gripper; // for storing the motion plan of the gripper move group interface

    // 3. Open the gripper
    move_group_interface_gripper.setJointValueTarget(move_group_interface_gripper.getNamedTargetValues("open")); // we are getting the pre defined position for the open of the gripper

    success = (move_group_interface_gripper.plan(my_plan_gripper) == moveit::planning_interface::MoveItErrorCode::SUCCESS); // the motion planner stores the motion plan for the gripper ka move group interface in my_plan_gripper variable.

    ROS_INFO_NAMED("tutorial", "Visualizing plan 1 (pose goal) %s", success ? "" : "FAILED");

    move_group_interface_gripper.move(); // then we move the end effector according to the motion plan.

    // 4. Move the TCP close to the object
    target_pose1.position.z = target_pose1.position.z - 0.2; // we are subtracting the offset value of 0.2 from the previous position of the arm, so that it moves down and comes closer to the blue box.
    move_group_interface_arm.setPoseTarget(target_pose1);

    success = (move_group_interface_arm.plan(my_plan_arm) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

    ROS_INFO_NAMED("tutorial", "Visualizing plan 1 (pose goal) %s", success ? "" : "FAILED");

    move_group_interface_arm.move();

    // 5. Close the  gripper
    move_group_interface_gripper.setJointValueTarget(move_group_interface_gripper.getNamedTargetValues("closed"));

    success = (move_group_interface_gripper.plan(my_plan_gripper) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

    ROS_INFO_NAMED("tutorial", "Visualizing plan 1 (pose goal) %s", success ? "" : "FAILED");

    move_group_interface_gripper.move();

    // 6. Move the TCP above the plate
    target_pose1.position.z = target_pose1.position.z + 0.2; // since we subtracted with 0.2 the z-axis ka value to move down, now for moving it up we will do the vice-versa which is adding 0.2 to the value of z-axis ka position.
    target_pose1.position.x = target_pose1.position.x - 0.6; // since we know that the plate is kept 0.6 meters towards the left of the current position of the arm in the x-direction we will be subtracting this 0.6 from the present x-axis ka position.
    move_group_interface_arm.setPoseTarget(target_pose1);

    success = (move_group_interface_arm.plan(my_plan_arm) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

    ROS_INFO_NAMED("tutorial", "Visualizing plan 1 (pose goal) %s", success ? "" : "FAILED");

    move_group_interface_arm.move();

    // 7. Lower the TCP above the plate
    target_pose1.position.z = target_pose1.position.z - 0.14; // again we subtract by 0.14 to move the robotic arm down.
    move_group_interface_arm.setPoseTarget(target_pose1); // we are setting the pose_target for the arm interface.

    success = (move_group_interface_arm.plan(my_plan_arm) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

    ROS_INFO_NAMED("tutorial", "Visualizing plan 1 (pose goal) %s", success ? "" : "FAILED");

    move_group_interface_arm.move();

    // 8. Open the gripper
    move_group_interface_gripper.setJointValueTarget(move_group_interface_gripper.getNamedTargetValues("open"));

    success = (move_group_interface_gripper.plan(my_plan_gripper) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

    ROS_INFO_NAMED("tutorial", "Visualizing plan 1 (pose goal) %s", success ? "" : "FAILED");

    move_group_interface_gripper.move();

  ros::shutdown();
  return 0;
}
