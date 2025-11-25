# Effectiveness of Face Masks in Indoor Public Settings for Prevention of COVID-19

# Overview

Mask effectiveness has been widely studied, but real-world indoor environments differ greatly in crowding, movement, and interaction patterns. This project investigates:

- How mask type (surgical vs. N95) affects infection counts
- How environment type influences spread
- How movement patterns shape exposure
- How infection curves differ over 20,000 simulation steps

The simulation incorporates mask-effectiveness data from CDC findings (page 2 of report):
Surgical masks: infection odds reduced by 66%
N95 masks: infection odds reduced by 83%


# Motivation

The project grows out of an OpenABM discussion in class (page 1). The CDC report motivating this work details how different mask types reduce the probability of testing positive for SARS-CoV-2. This simulation adopts those values and tests them in two contrasting Bard environments:

Kline cafeteria — crowded, high movement, high social contact

Biology lab — small, structured, low movement

The goal is to better understand how environmental differences amplify or reduce infection risks.

# Methods

The simulation proceeds in two stages (page 4):

Activity modeling (movement)

Each “step” is a movement update for every human.

Distances between humans determine whether contact occurs.

A contact radius approximates the 6-feet CDC guideline.

Intervention modeling (mask effectiveness)

If a susceptible human enters the infection radius of an infected human:

Apply surgical mask probability: 34% chance of infection

Apply N95 probability: 17% chance of infection

These values come from the CDC test-negative study (page 3).

# Simulation Architecture

The program uses four Java classes (page 5):

Class	Purpose
Human	Stores position, mask type, and infection status
Vector	Controls movement direction and step updates
Simulation	Runs environment logic for Kline or Lab
SimRunner	Executes full simulation loop

Each simulation runs for 20,000 steps per environment per mask type.

# Environments Modeled
1. Kline Dining Hall (page 1–2)

Large space

High density of people

Frequent close interactions

More chaotic movement

2. Biology Lab (page 6–7)

Small, structured room

Fewer total people

Low movement between stations

These conditions allow comparison of mask performance under high vs. low contact dynamics.

# Results
Kline Environment (Figure 1, page 6)

Surgical mask infections rise faster than N95 infections

N95 curve stays consistently lower

Total infections significantly higher than in the lab environment

At roughly 10,000 steps, both curves approach their maximum values

Biology Lab (Figure 2, page 7)

Both curves rise more slowly due to fewer interactions

Infection counts remain much lower overall

N95 curve remains consistently below surgical mask curve

The difference between mask types becomes more visible late in the simulation

# Data Analysis Highlights

From the analysis (page 7–8):

Surgical mask curves have steeper slopes, indicating faster spread

N95 masks produce slower, flatter curves, showing better protection

Environmental factors (crowding, motion, density) strongly influence total infection count

Kline produces far more infections because:

More people

More movement

More repeated close contacts

This aligns with the hypothesis: N95 masks reduce infections more effectively than surgical masks.

# Evaluation

As summarized on page 8–9:

N95 masks consistently outperform surgical masks

Mask type alone cannot eliminate risk in crowded spaces

Environmental structure matters significantly — Kline generates more infections than Lab

Movement and density are major drivers of spread in agent-based models

# Limitations

The simulation makes several simplifying assumptions (page 9):

Only one simulation run was performed for each condition

All humans have identical age, immunity, and susceptibility

No vaccination, variant differences, airflow models, or stochastic viral shedding

Agents move according to predefined movement rules (not learned behavior)

These limitations mean the results demonstrate patterns, not real-world predictions.

# Future Improvements

As proposed in the report (page 9):

Incorporate age variation and immunity levels

Add multiple simulation runs to compute mean ± variance

Include vaccination status, reinfection probability, and viral load

Introduce stochastic movement and airflow models

Simulate additional indoor spaces (library, gym, dorm hallway, etc.)

📁 Repository Structure
MaskSimulation/
│
├── src/
│   ├── Human.java
│   ├── Vector.java
│   ├── Simulation.java
│   └── SimRunner.java
│
├── results/
│   ├── kline_results.png
│   └── lab_results.png
│
└── report.pdf   # Full project report


(Update to match your repo’s actual structure.)

# How to Run

Compile:

javac *.java


Run:

java SimRunner
