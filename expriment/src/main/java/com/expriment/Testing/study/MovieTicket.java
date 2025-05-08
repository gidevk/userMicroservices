package com.expriment.Testing.study;

public class MovieTicket {
    private int movieId;
    private int noOfSeats;
    private double costPerTicket;
 
    // Constructor to initialize movieId and noOfSeats
    public MovieTicket(int movieId, int noOfSeats) {
        this.movieId = movieId;
        this.noOfSeats = noOfSeats;
        setCostPerTicket(movieId); // Set cost per ticket based on the movieId
    }
 
    // Method to calculate the total amount with 2% tax
    public double calculateTotalAmount() {
        double totalAmount = noOfSeats * costPerTicket;
        totalAmount += totalAmount * 0.02; // Adding 2% tax
        return Math.round(totalAmount); // Rounding off the result
    }
 
    // Getters and Setters for movieId
    public int getMovieId() {
        return movieId;
    }
 
    public void setMovieId(int movieId) {
        this.movieId = movieId;
        setCostPerTicket(movieId); // Update cost per ticket when movieId changes
    }
 
    // Getters and Setters for noOfSeats
    public int getNoOfSeats() {
        return noOfSeats;
    }
 
    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }
 
    // Getters and Setters for costPerTicket
    public double getCostPerTicket() {
        return costPerTicket;
    }
 
    public void setCostPerTicket(double costPerTicket) {
        this.costPerTicket = costPerTicket;
    }
 
    // Method to set cost per ticket based on movieId
    private void setCostPerTicket(int movieId) {
        switch (movieId) {
            case 111:
                this.costPerTicket = 7.0;
                break;
            case 112:
                this.costPerTicket = 8.0;
                break;
            case 113:
                this.costPerTicket = 8.5;
                break;
            default:
                this.costPerTicket = 0.0; // Default to 0 if movieId is invalid
        }
    }
    public static void main(String[] args) {
        // Creating an instance of MovieTicket with movieId 112 and 3 seats
        MovieTicket ticket = new MovieTicket(113, 3);
        // Calculating total amount
        double totalAmount = ticket.calculateTotalAmount();
        System.out.println("Total amount for booking: $" + totalAmount);
    }
}
 