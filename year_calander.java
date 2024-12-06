
//To display calendar without using Calendar class in java
import java.io.*;

class Calendar1 {
    static int year, day, g, cal[][];

    // function to accept year for which the calendar is to be printed
    public static void accept() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Year for which the calendar is to be displayed : ");
        year = Integer.parseInt(br.readLine());
        System.out.println();
    }

    // checking if the year is leap
    public static int is_leap(int y) {
        if (y % 4 == 0)
            return 1;
        else if (y % 100 == 0) {
            if (y % 4 == 0)
                return 1;
            else
                return 0;
        } else
            return 0;
    }

    // function to calculate number of days in a month
    public static void no_of_days(int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
            day = 31;
        else if (month == 2) {
            if (is_leap(year) == 1)// checking if month 2 belongs to leap year or not
                day = 29;
            else
                day = 28;
        } else
            day = 30;
    }

    // function to store names
    public static String mon(int m) {
        String name[] = { "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December" };
        return name[m - 1];
    }

    // finding day number of succeeding years
    public static void finding_dayno() {
        int f;
        if (year > 2000) {
            g = 6;
            for (f = 2001; f <= year; f++) {
                if (is_leap(f - 1) == 1)
                    g = g + 2;
                else
                    g++;
                if (g > 6)
                    g = g - 7;
            }
        } else if (year < 2000) {
            g = 6;
            for (f = 1999; f >= year; f--) {
                if (is_leap(f) == 1)
                    g = g - 2;
                else
                    g--;
                if (g < 0)
                    g = g + 7;
            }
        } else
            g = 6;
    }

    // assembling the calendar
    public static void print(int k, int l, int p) {
        int i, a;
        String days[] = { "S", "M", "T", "W", "Th", "F", "Sa" };
        System.out.println("\t    " + mon(k) + "\t\t\t     " + mon(l) + "\t\t\t     " + mon(p));
        System.out.print("\t");
        for (i = 1; i <= 3; i++)
            System.out.print("---------------------\t\t");
        System.out.println();
        System.out.print("\t");
        for (i = 1; i <= 3; i++) {
            for (a = 0; a < 7; a++)
                System.out.print(days[a] + "  ");
            System.out.print("\t\t");
        }
        System.out.println();
        System.out.print("\t");
        for (i = 1; i <= 3; i++)
            System.out.print("---------------------\t\t");
        System.out.println();
    }

    public static void initialize(int mm) {
        int d, b = 0, c = 0, h;
        cal = new int[5][7];
        no_of_days(mm);
        d = 0;
        for (b = g; b < 7; b++) {
            c++;
            if (c <= day)
                cal[d][b] = c;
        }
        for (d = 1; d < 5; d++) {
            for (b = 0; b < 7; b++) {
                c++;
                if (c <= day) {
                    if (c == day && b == 6)
                        g = 0;
                    cal[d][b] = c;
                } else {
                    g = b;
                    break;
                }
            }
        }
        h = day - c;
        if (h > 0) {
            g = h;
            d = 0;
            for (b = 0; b < h; b++) {
                c++;
                if (c <= day)
                    cal[d][b] = c;
            }
        }
    }

    // printing the dates with equal gaps
    public static void print_date(int q, int mmm[][]) {
        int e;
        for (e = 0; e < 7; e++) {
            if (mmm[q][e] != 0 && mmm[q][e] < 10)
                System.out.print(mmm[q][e] + "  ");// double gap
            else if (mmm[q][e] != 0 && mmm[q][e] >= 10)
                System.out.print(mmm[q][e] + " ");// single gap
            if (mmm[q][e] == 0)
                System.out.print("   ");// three gaps for no dates printed
        }
        System.out.print("\t\t");
    }

    // printing the calendar
    public static void main(String[] args) throws IOException {
        accept();
        System.out.println("\t\t\t      ********** CALENDAR OF THE YEAR **********");
        System.out.println("\t\t\t\t    -----------------------------");
        System.out.println("\t\t\t\t\t       " + year);
        System.out.println("\t\t\t\t    -----------------------------");
        System.out.println();
        finding_dayno();
        int w, x = 0, j = 0;
        int m1[][] = new int[5][7];
        int m2[][] = new int[5][7];
        int m3[][] = new int[5][7];
        for (w = 0; w < 4; w++)// 4 rows for 3 months in one row each
        {
            x++;
            print(x, x + 1, x + 2);// three consecutive months are printed
            initialize(x);
            m1 = cal;
            initialize(x + 1);
            m2 = cal;
            initialize(x + 2);
            m3 = cal;
            for (j = 0; j < 5; j++)// printing accordingly with gaps
            {
                System.out.print("\t");
                print_date(j, m1);
                print_date(j, m2);
                print_date(j, m3);
                System.out.println();
            }
            System.out.println();
            x = x + 2;
        }
    }
}