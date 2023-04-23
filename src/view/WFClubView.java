package view;

import java.util.*;

import model.Report;
import model.Customer;

public class WFClubView {

    private static final int maxCap = 5;
    private Map<String, ArrayList<Integer>> satFirst = new HashMap<>();
    private Map<String, ArrayList<Integer>> satSecond = new HashMap<>();
    private Map<String, ArrayList<Integer>> satThird = new HashMap<>();
    private Map<String, ArrayList<Integer>> satFourth = new HashMap<>();
    private Map<String, ArrayList<Integer>> sunFirst = new HashMap<>();
    private Map<String, ArrayList<Integer>> sunSecond = new HashMap<>();
    private Map<String, ArrayList<Integer>> sunThird = new HashMap<>();
    private Map<String, ArrayList<Integer>> sunFourth = new HashMap<>();
    private static final String[] fitLessons = {"Spin", "Yoga", "Bodysculpt", "Zumba", "Aquacise", "Box Fit"};
    private static final String[] fitLessonsFees = {"20", "30", "25", "15", "10", "12"};
    private static Scanner scanner = new Scanner(System.in);

    public WFClubView() {

        int choice = -1;
        while (choice != 99) {
            System.out.println("Weekend Fitness Club");
            System.out.println("1. Book fitness lesson");
            System.out.println("2. Change a booking");
            System.out.println("3. Cancel a booking");
            System.out.println("4. Add a review");
            System.out.println("5. Monthly lesson report");
            System.out.println("6. Monthly champion fitness type report");
            System.out.println("7. Display Timetable based on Day and Weekend");
            System.out.println("99. Exit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bookLesson();
                    break;
                case 2:
                    changeBooking();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    review();
                    break;
                case 5:
                    lessonReport();
                    break;
                case 6:
                    fitnessLessonReport();
                    break;
                case 7:
                    Scanner inp = new Scanner(System.in);
                    System.out.print("Enter the day (Saturday or Sunday): ");
                    String day = inp.nextLine();
                    System.out.print("Enter the weekend: ");
                    String weekend = inp.nextLine();
                    showTimetable(weekend, day,0);
                    break;
                case 99:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    public boolean bookLesson() {
        // Book a lesson
        Scanner bookScanner = new Scanner(System.in);
        System.out.print("Enter the day (Saturday or Sunday): ");
        String day = bookScanner.nextLine();
        System.out.print("Enter the weekend for booking ");
        String weekend = bookScanner.nextLine();
        showTimetable(weekend, day, 0);
        System.out.print("Enter the lesson type: ");
        String lessonType = bookScanner.nextLine();
        System.out.print("Enter your customer ID: ");
        int customerId = bookScanner.nextInt();

        Map<String, ArrayList<Integer>> lessons = null;
        if (day.equals("Saturday")) {
            if (weekend.equals("1")) lessons = satFirst;
            else if (weekend.equals("2")) lessons = satSecond;
            else if (weekend.equals("3")) lessons = satThird;
            else if (weekend.equals("4")) lessons = satFourth;

        } else if (day.equals("Sunday")) {

            if (weekend.equals("1")) lessons = sunFirst;
            else if (weekend.equals("2")) lessons = sunSecond;
            else if (weekend.equals("3")) lessons = sunThird;
            else if (weekend.equals("4")) lessons = sunFourth;

        } else {
            System.out.println("Sorry, invalid day. try again");

            return false;
        }

        int lessonIndex = Arrays.asList(fitLessons).indexOf(lessonType);
        if (lessonIndex == -1) {
            System.out.println("Sorry, invalid lesson. try again");
            return false;
        }

        ArrayList<Integer> customers = lessons.getOrDefault(lessonType + day + customerId, new ArrayList<>());

        if (customers.size() >= maxCap) {
            System.out.println("Sorry, this lesson is full.");
            return false;
        } else if (customers.contains(customerId)) {
            System.out.println("You have already booked this lesson.");
            return false;
        } else {
            customers.add(customerId);
            lessons.put(lessonType + day + customerId, customers);
            System.out.println("Booking successful.");
            showTimetable(weekend, day, customerId);

            return true;
        }

    }
    public boolean changeBooking() {
        // Method for changing the current booking
        Scanner bookScanner = new Scanner(System.in);
        System.out.print("Enter the day (Saturday or Sunday): ");
        String day = bookScanner.nextLine();
        System.out.print("Enter the weekend for change lesson (1to 4) ");
        String weekend = bookScanner.nextLine();
        System.out.print("Enter the lesson type: ");
        String lessonType = bookScanner.nextLine();
        System.out.print("Enter your customer ID: ");
        int customerId = bookScanner.nextInt();

        Map<String, ArrayList<Integer>> lessons = null;
        if (day.equals("Saturday")) {
            if (weekend.equals("1")) lessons = satFirst;
            if (weekend.equals("2")) lessons = satSecond;
            if (weekend.equals("3")) lessons = satThird;
            if (weekend.equals("4")) lessons = satFourth;

        } else if (day.equals("Sunday")) {
            if (weekend.equals("1")) lessons = sunFirst;
            if (weekend.equals("2")) lessons = sunSecond;
            if (weekend.equals("3")) lessons = sunThird;
            if (weekend.equals("4")) lessons = sunFourth;

        } else {
            System.out.println("Sorry, invalid day. try again");
            return false;
        }

        int lessonIndex = Arrays.asList(fitLessons).indexOf(lessonType);
        if (lessonIndex == -1) {
            System.out.println("Sorry, invalid lesson. try again");
            return false;
        }

        ArrayList<Integer> customers = lessons.getOrDefault(lessonType + day + customerId, new ArrayList<>());
        if (customers.contains(customerId)) {
            Scanner changeBookScanner = new Scanner(System.in);
            System.out.print("Enter the new day (Saturday or Sunday): ");
            String newDay = changeBookScanner.nextLine();
            System.out.print("Enter the new lesson type: ");
            String newLessonType = changeBookScanner.nextLine();

            Map<String, ArrayList<Integer>> newLessons = null;
            if (newDay.equals("Saturday")) {
                newLessons = satFirst;
            } else if (newDay.equals("Sunday")) {
                newLessons = sunFirst;
            } else {
                System.out.println("Sorry, invalid day. try again");
                return false;
            }
            if (newDay.equalsIgnoreCase(day) && newLessonType.equalsIgnoreCase(lessonType)) {
                System.out.println("Sorry, Asking same request to edit");
                return false;
            }
            int newLessonIndex = Arrays.asList(fitLessons).indexOf(newLessonType);
            if (newLessonIndex == -1) {
                System.out.println("Sorry, invalid lesson. try again");
                return false;
            }

            ArrayList<Integer> newCustomers = newLessons.getOrDefault(newLessonType + newDay, new ArrayList<>());
            if (newCustomers.size() >= maxCap) {
                System.out.println("Sorry, this lesson is full.");
                return false;
            } else if (newCustomers.contains(customerId)) {
                System.out.println("You have already booked this lesson.");
                return false;
            } else {
                newCustomers.add(customerId);
                newLessons.put(newLessonType + newDay, newCustomers);
                lessons.remove(lessonType + day + customerId, customers);
                System.out.println("Booking Updated successfully.");
                return true;
            }
        } else {
            System.out.println("Invalid Booking");
            return false;
        }

    }
    public boolean cancelBooking() {
        // For cancelling the current booking
    	
        Scanner bookScanner = new Scanner(System.in);
        System.out.print("Enter the day (Saturday or Sunday): ");
        String day = bookScanner.nextLine();
        System.out.print("Enter the weekend for cadddncel booking (1 to 4) ");
        String weekend = bookScanner.nextLine();
        System.out.print("Enter the lesson type: ");
        String lessonType = bookScanner.nextLine();
        System.out.print("Enter your customer ID: ");
        int customerId = bookScanner.nextInt();

        Map<String, ArrayList<Integer>> lessons = null;
        if (day.equals("Saturday")) {
            if (weekend.equals("1")) lessons = satFirst;
            if (weekend.equals("2")) lessons = satSecond;
            if (weekend.equals("3")) lessons = satThird;
            if (weekend.equals("4")) lessons = satFourth;

        } else if (day.equals("Sunday")) {
            if (weekend.equals("1")) lessons = sunFirst;
            if (weekend.equals("2")) lessons = sunSecond;
            if (weekend.equals("3")) lessons = sunThird;
            if (weekend.equals("4")) lessons = sunFourth;

        } else {
            System.out.println("Sorry, invalid day. try again");
            return false;
        }

        int lessonIndex = Arrays.asList(fitLessons).indexOf(lessonType);
        if (lessonIndex == -1) {
            System.out.println("Sorry, invalid lesson. try again");
            return false;
        }

        ArrayList<Integer> customers = lessons.getOrDefault(lessonType + day + customerId, new ArrayList<>());
        if (customers.contains(customerId)) {
            lessons.remove(lessonType + day + customerId, customers);
            System.out.println("Booking Cancel successful.");
            return true;
        } else {
            System.out.println("Invalid Booking Try again.");
            return false;
        }

    }

    private void review() {

        HashMap<String, ArrayList<String>> reviewsByCustomer = new HashMap<String, ArrayList<String>>();
        HashMap<String, ArrayList<Integer>> ratingsByCustomer = new HashMap<String, ArrayList<Integer>>();
        HashMap<String, ArrayList<String>> reviewLessonByCustomer = new HashMap<String, ArrayList<String>>();

        // Prompt user for reviews and ratings
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a customer ID (or 'q' to quit): ");
            String customerID = sc.nextLine();
            if (customerID.equals("q")) {
                break;
            }
            System.out.print("Enter a Lesson to Review: ");
            String reviewLesson = sc.nextLine();
            System.out.print("Enter a review: ");
            String review = sc.nextLine();
            System.out.print("Enter a rating (1-5): ");
            int rating = sc.nextInt();
            sc.nextLine();

            // Add review and rating to customer's arrays
            if (!reviewsByCustomer.containsKey(customerID)) {
                reviewsByCustomer.put(customerID, new ArrayList<String>());
            }
            reviewsByCustomer.get(customerID).add(review);
            if (!ratingsByCustomer.containsKey(customerID)) {
                ratingsByCustomer.put(customerID, new ArrayList<Integer>());
            }
            ratingsByCustomer.get(customerID).add(rating);
            if (!reviewLessonByCustomer.containsKey(customerID)) {
                reviewLessonByCustomer.put(customerID, new ArrayList<String>());
            }
            reviewLessonByCustomer.get(customerID).add(reviewLesson);

        }

        // Display reviews and ratings by customer
        for (String customerId : reviewsByCustomer.keySet()) {
            System.out.println("-----------------------------");
            System.out.println("Customer ID: " + customerId);
            ArrayList<String> reviews = reviewsByCustomer.get(customerId);
            ArrayList<Integer> ratings = ratingsByCustomer.get(customerId);
            ArrayList<String> lessons = reviewLessonByCustomer.get(customerId);
            for (int i = 0; i < reviews.size(); i++) {
                System.out.println("- LessonType: " + lessons.get(i));
                System.out.println("- Review: " + reviews.get(i));
                int rating = ratings.get(i);
                System.out.println("  Rating: " + getRatingMessage(rating));
                System.out.println("-----------------------------");
            }
        }
    }

    private String getRatingMessage(int rating) {
        switch (rating) {
            case 1:
                return "1 (We regret to learn that. Appreciate your input.)";
            case 2:
                return "2 (We value your opinions and will apply them to better our service.)";
            case 3:
                return "3 (We appreciate your comments. Sorry to hear that your experience was around average.)";
            case 4:
                return "4 (We appreciate your input. Happy to hear that your experience was positive.)";
            case 5:
                return "5 (Thank you for your feedback. We are so happy to hear you had a great time!)";
            default:
                return "Invalid rating";
        }
    }
    public void lessonReport() {
        // Static data of some lessons and their ratings
        Map<String, Integer> lessonRatings = new HashMap<>();
        lessonRatings.put("Spin", 4);
        lessonRatings.put("Yoga", 3);
        lessonRatings.put("Bodysculpt", 5);
        lessonRatings.put("Zumba", 2);
        lessonRatings.put("Aquacise", 2);
        lessonRatings.put("Zumba", 2);


        // Static customer list for generating report
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Leo", "Spin", "Saturday"));
        customers.add(new Customer("Aimee", "Spin", "Sunday"));
        customers.add(new Customer("Ricky", "Aquacise", "Saturday"));
        customers.add(new Customer("Chris", "Zumba", "Sunday"));
        customers.add(new Customer("Nolljean", "Yoga", "Saturday"));
        customers.add(new Customer("sam", "Spin", "Sunday"));
        customers.add(new Customer("Linto", "Spin", "Saturday"));
        customers.add(new Customer("iliies", "Bodysculpt", "Sunday"));


        // Initialize a map that will store the number of customers per lesson on each day
        Map<String, Map<String, Integer>> customerCountByLessonByDay = new HashMap<>();
        // Initialize a map that will store the sum of ratings per lesson
        Map<String, Integer> ratingSumByLesson = new HashMap<>();
        // Initialize a map that will store the number of ratings per lesson
        Map<String, Integer> ratingCountByLesson = new HashMap<>();

        // Loop through all customers and update the customerCountByLessonByDay map
        for (Customer customer : customers) {
            String lessonName = customer.getLessonName();
            String day = customer.getDay();
            Map<String, Integer> customerCountByLesson = customerCountByLessonByDay.computeIfAbsent(day, k -> new HashMap<>());
            customerCountByLesson.put(lessonName, customerCountByLesson.getOrDefault(lessonName, 0) + 1);
        }

        // Loop through all lessons and update the ratingSumByLesson and ratingCountByLesson maps
        for (String lessonName : lessonRatings.keySet()) {
            int rating = lessonRatings.get(lessonName);
            ratingSumByLesson.put(lessonName, 0);
            ratingCountByLesson.put(lessonName, 0);
            for (String day : customerCountByLessonByDay.keySet()) {
                Map<String, Integer> customerCountByLesson = customerCountByLessonByDay.get(day);
                if (customerCountByLesson.containsKey(lessonName)) {
                    int customerCount = customerCountByLesson.get(lessonName);
                    ratingSumByLesson.put(lessonName, ratingSumByLesson.get(lessonName) + rating * customerCount);
                    ratingCountByLesson.put(lessonName, ratingCountByLesson.get(lessonName) + customerCount);
                }
            }
        }

        // Print the report
        System.out.println("Lesson Report:");
        System.out.println("---------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %10s | %10s |%n", "Day", "Lesson", "Customers", "Average Rating");
        System.out.println("---------------------------------------------------------");
        for (String day : customerCountByLessonByDay.keySet()) {
            Map<String, Integer> customerCountByLesson = customerCountByLessonByDay.get(day);
            for (String lessonName : customerCountByLesson.keySet()) {
                int customerCount = customerCountByLesson.get(lessonName);
                int ratingSum = ratingSumByLesson.get(lessonName);
                int ratingCount = ratingCountByLesson.get(lessonName);
                double averageRating = (double) ratingSum / ratingCount;
                System.out.printf("| %-10s | %-10s | %10s | %10s |%n", day, lessonName, customerCount, averageRating);
                System.out.println("---------------------------------------------------------");
            }
        }
    }

    public void fitnessLessonReport() {


        // Create a list of FitnessLesson objects
        List<Report> lessons = new ArrayList<>();
        lessons.add(new Report("Yoga", 10, 50));
        lessons.add(new Report("Pilates", 20, 75));
        lessons.add(new Report("Zumba", 30, 100));
        lessons.add(new Report("Yoga", 15, 60));
        lessons.add(new Report("Pilates", 25, 90));
        lessons.add(new Report("Zumba", 35, 120));
        lessons.add(new Report("Yoga", 20, 70));
        lessons.add(new Report("Pilates", 30, 105));
        lessons.add(new Report("Zumba", 40, 135));

        // Calculate the total income for each type of lesson
        Map<String, Integer> incomeByLesson = new HashMap<>();
        for (Report lesson : lessons) {
            String lessonType = lesson.getType();
            int lessonIncome = lesson.getPrice() * lesson.getAttendees();
            if (incomeByLesson.containsKey(lessonType)) {
                int totalIncome = incomeByLesson.get(lessonType) + lessonIncome;
                incomeByLesson.put(lessonType, totalIncome);
            } else {
                incomeByLesson.put(lessonType, lessonIncome);
            }
        }

        // Determine the lesson type with the highest income
        String highestIncomeLessonType = "";
        int highestIncome = 0;
        for (String lessonType : incomeByLesson.keySet()) {
            int totalIncome = incomeByLesson.get(lessonType);
            if (totalIncome > highestIncome) {
                highestIncomeLessonType = lessonType;
                highestIncome = totalIncome;
            }
        }

        // Print the report
        System.out.println("Report: Type of fitness lesson with highest income");
        System.out.println("Lesson type\tIncome");
        for (String lessonType : incomeByLesson.keySet()) {
            int totalIncome = incomeByLesson.get(lessonType);
            System.out.println(lessonType + "\t\t$" + totalIncome);
        }
        System.out.println("\nLesson type with highest income: " + highestIncomeLessonType);
    }

    public void showTimetable(String weekend, String day, int custId) {
        System.out.println("_____________________ Timetable ______________________");

        Map<String, ArrayList<Integer>> lessons;
        if (day.equalsIgnoreCase("saturday")) {
            switch (weekend) {
                case "1":
                    System.out.println("SATURDAY FIRST WEEK:");
                    lessons = satFirst;
                    break;
                case "2":
                    System.out.println("SATURDAY SECOND WEEK:");
                    lessons = satSecond;
                    break;
                case "3":
                    System.out.println("SATURDAY THIRD WEEK:");
                    lessons = satThird;
                    break;
                default:
                    System.out.println("SATURDAY FOURTH WEEK:");
                    lessons = satFourth;
                    break;
            }
        } else if (day.equalsIgnoreCase("sunday")) {
            switch (weekend) {
                case "1":
                    System.out.println("SUNDAY FIRST WEEK:");
                    lessons = sunFirst;
                    break;
                case "2":
                    System.out.println("SUNDAY SECOND WEEK:");
                    lessons = sunSecond;
                    break;
                case "3":
                    System.out.println("SUNDAY THIRD WEEK:");
                    lessons = sunThird;
                    break;
                default:
                    System.out.println("SUNDAY FOURTH WEEK:");
                    lessons = sunFourth;
                    break;
            }
        } else {
            System.out.println("Invalid day input.");
            return;
        }

        System.out.printf("| %-10s | %-8s | %-8s | %4s |%n", "LESSON", "SLOT BOOKED", "FEES", "SLOT AVAIL");
        matching(day, fitLessons, lessons);

        System.out.println("______________________________________________________");
        System.out.println();
    }

    public void matching(String daySelect, String[] fitnessLessons, Map<String, ArrayList<Integer>> lessonMap) {

        String pattern = "";
        for (String lesson : fitnessLessons) {
            int count = 0;
            pattern = lesson + daySelect;
            for (String key : lessonMap.keySet()) {
                if (key.startsWith(pattern)) {
                    count++;
                }
            }
            String fee = fitLessonsFees[Arrays.asList(fitnessLessons).indexOf(lesson)];
            System.out.printf("| %-10s | %-8s    | %-8s    | %-8s  |%n", lesson, count, fee, maxCap-count);
        }

    }

}
