import java.io.*;

public class Main {
    public static enum TokenType {
        // Following the lexical rules of MIPS64 specs, the language has 4 token types:
        KEYWORD, GPR, FPR, ERROR;
        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static enum State{
        // States as defined by DFA
        Q0,Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10,Q11,Q12,Q13,Q14,Q15,Q16,Q17,Q18,Q19, Q20, Qnull;
    }

    private static State DFA(State currentState, char letter){
        State newState = State.Qnull;
        if(currentState == State.Q0){
            switch (letter){
                case 'D':
                    return State.Q1;
                case 'R':
                    return State.Q2;
                case '$':
                    return State.Q3;
                case 'F':
                    return State.Q4;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q1){
            switch (letter){
                case 'M':
                    return State.Q10;
                case 'A':
                    return State.Q5;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q2){
            switch (letter){
                case '3':
                    return State.Q15;
                case '1':
                case '2':
                    return State.Q16;
                case '0':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    return State.Q17;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q3){
            switch (letter){
                case '3':
                    return State.Q15;
                case '1':
                case '2':
                    return State.Q16;
                case '0':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    return State.Q17;
                case 'F':
                    return State.Q4;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q4){
            switch (letter){
                case '3':
                    return State.Q18;
                case '1':
                case '2':
                    return State.Q19;
                case '0':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    return State.Q20;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q5){
            switch (letter){
                case 'D':
                    return State.Q6;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q6){
            switch (letter){
                case 'D':
                    return State.Q7;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q7){
            switch (letter){
                case 'U':
                    return State.Q8;
                case 'I':
                    return State.Q9;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q8){
            switch (letter){
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q9){
            switch (letter){
                case 'U':
                    return State.Q8;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q10){
            switch (letter){
                case 'U':
                    return State.Q11;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q11){
            switch (letter){
                case 'L':
                    return State.Q12;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q12){
            switch (letter){
                case 'T':
                    return State.Q13;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q13){
            switch (letter){
                case 'U':
                    return State.Q14;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q14){
            switch (letter){
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q15){
            switch (letter){
                case '0':
                case '1':
                    return State.Q17;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q16){
            switch (letter){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    return State.Q17;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q17){
            switch (letter){
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q18){
            switch (letter){
                case '0':
                case '1':
                    return State.Q20;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q19){
            switch (letter){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    return State.Q20;
                default:
                    return  State.Qnull;
            }
        }

        if(currentState == State.Q20){
            switch (letter){
                default:
                    return  State.Qnull;
            }
        }

        return newState;
    }

    public static void main(String[] args) {

        if(args.length < 1) {
            System.out.println("Usage: java Main \"(input_filename.txt)\".");
            return;
        }

        String filename = args[0];
        String filePath = new File("").getAbsolutePath();

        try {
            // Read input file
            FileReader fr =new FileReader(filePath + "/src/" + filename );
            BufferedReader br = new BufferedReader(fr);

            // Create output file
            PrintWriter out = new PrintWriter(new FileWriter(filePath + "/src/" + "output.txt"));

            int i;
            State currentState = State.Q0;
            String outputLine = "";
            while((i=br.read())!=-1){
                // Check delimiters: space, comma, newline.
                if((char)i == ' ' || (char)i == ',' || (char)i == '\n' || (char)i == '\r'){
                    // for double delimiters ignore
                    if(currentState != State.Q0){
                        // Check end states
                        switch (currentState){
                            case Q8:
                            case Q13:
                            case Q14:
                                outputLine += TokenType.KEYWORD.toString() + " ";
                                break;

                            case Q15:
                            case Q16:
                            case Q17:
                                outputLine += TokenType.GPR.toString() + " ";
                                break;

                            case Q18:
                            case Q19:
                            case Q20:
                                outputLine += TokenType.FPR.toString() + " ";
                                break;

                            case Qnull:
                                outputLine += TokenType.ERROR.toString() + " ";
                        }
                    }
                    // if next line print output and reset output line
                    if((char)i == '\n'){
                        out.println(outputLine);
                        outputLine = "";
                    }
                    currentState = State.Q0;
                }
                else{
                    currentState = DFA(currentState, (char)i);
                }
            }
            br.close();
            fr.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}