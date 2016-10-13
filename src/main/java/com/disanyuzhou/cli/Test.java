package com.disanyuzhou.cli;


import com.google.common.base.Preconditions;
import org.apache.commons.cli2.CommandLine;
import org.apache.commons.cli2.Group;
import org.apache.commons.cli2.Option;
import org.apache.commons.cli2.OptionException;
import org.apache.commons.cli2.builder.ArgumentBuilder;
import org.apache.commons.cli2.builder.DefaultOptionBuilder;
import org.apache.commons.cli2.builder.GroupBuilder;
import org.apache.commons.cli2.commandline.Parser;
import org.apache.commons.cli2.option.DefaultOption;
import org.apache.commons.cli2.util.HelpFormatter;
import org.apache.commons.io.Charsets;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by make on 6/10/16.
 */
public class Test {

    private static Option inputOption;
    private static Option outputOption;
    private static final String INPUT_OPTION = "input";
    public static final String OUTPUT_OPTION = "output";
    private static List<Option> options = new ArrayList<Option>();
    private static Group group;
    protected static Path inputPath;
    protected static File inputFile; //the input represented as a file
    protected static Path outputPath;
    protected static File outputFile; //the input represented as a file
    protected static  Map<String, List<String>> argMap;
    static Path tempPath;



    public static void main(String[] args) throws IOException {

        inputOption = new DefaultOptionBuilder()
                .withLongName(INPUT_OPTION)
                .withRequired(false)
                .withShortName("i")
                .withArgument(
                        new ArgumentBuilder().withName(INPUT_OPTION).withMinimum(1)
                                .withMaximum(1).create())
                .withDescription("The directory pathname for input.").create();
        options.add(inputOption);
        System.out.println(inputOption);

        outputOption = new DefaultOptionBuilder()
                .withLongName(OUTPUT_OPTION)
                .withRequired(false)
                .withShortName("o")
                .withArgument(
                        new ArgumentBuilder().withName(OUTPUT_OPTION).withMinimum(1)
                                .withMaximum(1).create())
                .withDescription("The directory pathname for output.").create();
        options.add(outputOption);
        System.out.println(outputOption);


        addOption("numRecommendations", "n", "Number of recommendations per user", String.valueOf(10));
        addOption("usersFile", null, "File of users to recommend for", null);
        addOption("itemsFile", null, "File of items to recommend for", null);
        addOption("filterFile", "f", "File containing comma-separated userID,itemID pairs. Used to exclude the item from "
                + "the recommendations for that user (optional)", null);
        addOption("userItemFile", "uif", "File containing comma-separated userID,itemID pairs (optional). "
                + "Used to include only these items into recommendations. "
                + "Cannot be used together with usersFile or itemsFile", null);
        addOption("booleanData", "b", "Treat input as without pref values", Boolean.FALSE.toString());
        addOption("maxPrefsPerUser", "mxp",
                "Maximum number of preferences considered per user in final recommendation phase",
                String.valueOf(10));
        addOption("minPrefsPerUser", "mp", "ignore users with less preferences than this in the similarity computation "
                + "(default: " + 1 + ')', String.valueOf(1));
        addOption("maxSimilaritiesPerItem", "m", "Maximum number of similarities considered per item ",
                String.valueOf(100));
        addOption("maxPrefsInItemSimilarity", "mpiis", "max number of preferences to consider per user or item in the "
                + "item similarity computation phase, users or items with more preferences will be sampled down (default: "
                + 500 + ')', String.valueOf(500));

        addOption("similarityClassname", "s", "Name of distributed similarity measures class to instantiate, "
                + "alternatively use one of the predefined similarities (" + "this" + ')', true);

        addOption("threshold", "tr", "discard item pairs with a similarity value below this", false);
        addOption("outputPathForSimilarityMatrix", "opfsm", "write the item similarity matrix to this path (optional)",
                false);
        addOption("randomSeed", null, "use this seed for sampling", false);

        addFlag("sequencefileOutput", null, "write the output into a SequenceFile instead of a text file");



        Map<String, List<String>> parsedArgs = parseArguments(args);
        if (parsedArgs == null) {
            System.out.println("exit(-1)");
            return;
        }

        Path outputPath = getOutputPath();
        int numRecommendations = Integer.parseInt(getOption("numRecommendations"));
        String usersFile = getOption("usersFile");
        String itemsFile = getOption("itemsFile");
        String filterFile = getOption("filterFile");
        String userItemFile = getOption("userItemFile");
        boolean booleanData = Boolean.valueOf(getOption("booleanData"));
        int maxPrefsPerUser = Integer.parseInt(getOption("maxPrefsPerUser"));
        int minPrefsPerUser = Integer.parseInt(getOption("minPrefsPerUser"));
        int maxPrefsInItemSimilarity = Integer.parseInt(getOption("maxPrefsInItemSimilarity"));
        int maxSimilaritiesPerItem = Integer.parseInt(getOption("maxSimilaritiesPerItem"));
        String similarityClassname = getOption("similarityClassname");
        double threshold = hasOption("threshold")
                ? Double.parseDouble(getOption("threshold")) : 0x0.0000000000001P-1022;
        long randomSeed = hasOption("randomSeed")
                ? Long.parseLong(getOption("randomSeed")) : 0x8000000000000000L;


        Path prepPath = getTempPath("preparePreferenceMatrix");
        Path similarityMatrixPath = getTempPath("similarityMatrix");
        Path explicitFilterPath = getTempPath("explicitFilterPath");
        Path partialMultiplyPath = getTempPath("partialMultiply");

        AtomicInteger currentPhase = new AtomicInteger();

        int numberOfUsers = -1;


        System.out.println(shouldRunNextPhase(parsedArgs, currentPhase));
        System.out.println(shouldRunNextPhase(parsedArgs, currentPhase));
        System.out.println(shouldRunNextPhase(parsedArgs, currentPhase));






    }

    protected static boolean shouldRunNextPhase(Map<String, List<String>> args, AtomicInteger currentPhase) {
        int phase = currentPhase.getAndIncrement();
        String startPhase = getOption(args, "--startPhase");
        String endPhase = getOption(args, "--endPhase");
        boolean phaseSkipped = (startPhase != null && phase < Integer.parseInt(startPhase))
                || (endPhase != null && phase > Integer.parseInt(endPhase));
        if (phaseSkipped) {
            System.out.println(String.format("Skipping phase {}", phase));
        }
        return !phaseSkipped;
    }


    protected static Path getOutputPath() {
        return outputPath;
    }

    protected static Path getOutputPath(String path) {
        return new Path(outputPath, path);
    }

    protected static File getInputFile() {
        return inputFile;
    }

    protected static File getOutputFile() {
        return outputFile;
    }


    protected static Path getTempPath() {
        return tempPath;
    }

    protected static Path getTempPath(String directory) {
        return new Path(tempPath, directory);
    }


    public static void addOption(String name, String shortName, String description, boolean required) {
        options.add(buildOption(name, shortName, description, true, required, null));
    }

    public static Option buildOption(String name,
                                        String shortName,
                                        String description,
                                        boolean hasArg,
                                        boolean required,
                                        String defaultValue) {

        return buildOption(name, shortName, description, hasArg, 1, 1, required, defaultValue);
    }

    public static Option buildOption(String name,
                                        String shortName,
                                        String description,
                                        boolean hasArg, int min, int max,
                                        boolean required,
                                        String defaultValue) {

        DefaultOptionBuilder optBuilder = new DefaultOptionBuilder().withLongName(name).withDescription(description)
                .withRequired(required);

        if (shortName != null) {
            optBuilder.withShortName(shortName);
        }

        if (hasArg) {
            ArgumentBuilder argBuilder = new ArgumentBuilder().withName(name).withMinimum(min).withMaximum(max);

            if (defaultValue != null) {
                argBuilder = argBuilder.withDefault(defaultValue);
            }

            optBuilder.withArgument(argBuilder.create());
        }
        DefaultOption defaultOption = optBuilder.create();
        System.out.println(defaultOption);
        return defaultOption;
    }

    public static void addFlag(String name, String shortName, String description) {
        options.add(buildOption(name, shortName, description, false, false, null));
    }


    protected static void addOption(String name, String shortName, String description, String defaultValue) {
        options.add(buildOption(name, shortName, description, true, false, defaultValue));
    }



    /** Add an arbitrary option to the set of options this job will parse when
     *  {@link #parseArguments(String[])} is called. If this option has no
     *  argument, use {@code containsKey} on the map returned by
     *  {@code parseArguments} to check for its presence. Otherwise, the
     *  string value of the option will be placed in the map using a key
     *  equal to this options long name preceded by '--'.
     * @return the option added.
     */
    protected static Option addOption(Option option) {
        options.add(option);
        return option;
    }




    /** Parse the arguments specified based on the options defined using the
     *  various {@code addOption} methods. If -h is specified or an
     *  exception is encountered print help and return null. Has the
     *  side effect of setting inputPath and outputPath
     *  if {@code addInputOption} or {@code addOutputOption}
     *  or {@code mapred.input.dir} or {@code mapred.output.dir}
     *  are present in the Configuration.
     *
     * @return a {@code Map<String,String>} containing options and their argument values.
     *  The presence of a flag can be tested using {@code containsKey}, while
     *  argument values can be retrieved using {@code get(optionName)}. The
     *  names used for keys are the option name parameter prefixed by '--'.
     *
     * @see #parseArguments(String[], boolean, boolean)  -- passes in false, false for the optional args.
     */
    public static Map<String, List<String>> parseArguments(String[] args) throws IOException {
        return parseArguments(args, false, false);
    }

    /**
     *
     * @param args  The args to parse
     * @param inputOptional if false, then the input option, if set, need not be present.  If true and input is an option
     *                      and there is no input, then throw an error
     * @param outputOptional if false, then the output option, if set, need not be present.  If true and output is an
     *                       option and there is no output, then throw an error
     * @return the args parsed into a map.
     */
    public static Map<String, List<String>> parseArguments(String[] args, boolean inputOptional, boolean outputOptional)
            throws IOException {

        Option helpOpt = addOption(new DefaultOptionBuilder().withLongName("help")
                .withDescription("Print out help").withShortName("h").create());
        addOption("tempDir", null, "Intermediate output directory", "temp");
        addOption("startPhase", null, "First phase to run", "0");
        addOption("endPhase", null, "Last phase to run", String.valueOf(Integer.MAX_VALUE));

        GroupBuilder gBuilder = new GroupBuilder().withName("Job-Specific Options:");

        for (Option opt : options) {
            gBuilder = gBuilder.withOption(opt);
        }

        group = gBuilder.create();

        CommandLine cmdLine;
        try {
            Parser parser = new Parser();
            parser.setGroup(group);
            parser.setHelpOption(helpOpt);
            cmdLine = parser.parse(args);

        } catch (OptionException e) {
            System.out.println(e.getMessage());
            CommandLineUtil.printHelpWithGenericOptions(group, e);
            return null;
        }

        if (cmdLine.hasOption(helpOpt)) {
            CommandLineUtil.printHelpWithGenericOptions(group);
            return null;
        }

        try {
            System.out.println("===== " + cmdLine);
            parseDirectories(cmdLine, inputOptional, outputOptional);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            CommandLineUtil.printHelpWithGenericOptions(group);
            return null;
        }

        argMap = new TreeMap<String, List<String>>();
        maybePut(argMap, cmdLine, options.toArray(new Option[options.size()]));

        tempPath = new Path(getOption("tempDir"));

        if (!hasOption("quiet")) {
             System.out.println(String.format("Command line arguments: {}", argMap));
        }
        return argMap;
    }

    public static String getOption(Map<String, List<String>> args, String optName) {
        List<String> res = args.get(optName);
        if (res != null && !res.isEmpty()) {
            return res.get(0);
        }
        return null;
    }

    public static String getOption(String optionName) {
        List<String> list = argMap.get(keyFor(optionName));
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public  static String keyFor(String optionName) {
        return "--" + optionName;
    }


    public static boolean hasOption(String optionName) {
        return argMap.containsKey(keyFor(optionName));
    }



    protected static void maybePut(Map<String, List<String>> args, CommandLine cmdLine, Option... opt) {
        for (Option o : opt) {

            // the option appeared on the command-line, or it has a value
            // (which is likely a default value).
            if (cmdLine.hasOption(o) || cmdLine.getValue(o) != null
                    || (cmdLine.getValues(o) != null && !cmdLine.getValues(o).isEmpty())) {

                // nulls are ok, for cases where options are simple flags.
                List<?> vo = cmdLine.getValues(o);
                if (vo != null && !vo.isEmpty()) {
                    List<String> vals = new ArrayList();
                    for (Object o1 : vo) {
                        vals.add(o1.toString());
                    }
                    args.put(o.getPreferredName(), vals);
                } else {
                    args.put(o.getPreferredName(), null);
                }
            }
        }
    }




    protected static void parseDirectories(CommandLine cmdLine, boolean inputOptional, boolean outputOptional)
    {

        Configuration conf = new Configuration();

        if (inputOption != null && cmdLine.hasOption(inputOption))
        {
            inputPath = new Path(cmdLine.getValue(inputOption).toString());
            inputFile = new File(cmdLine.getValue(inputOption).toString());
        }
        if (inputPath == null && conf.get("mapred.input.dir") != null)
        {
            inputPath = new Path(conf.get("mapred.input.dir"));
        }

        if (outputOption != null && cmdLine.hasOption(outputOption))
        {
            outputPath = new Path(cmdLine.getValue(outputOption).toString());
            outputFile = new File(cmdLine.getValue(outputOption).toString());
        }
        if (outputPath == null && conf.get("mapred.output.dir") != null)
        {
            outputPath = new Path(conf.get("mapred.output.dir"));
        }

        Preconditions.checkArgument(inputOptional || inputOption == null || inputPath != null,
                "No input specified or -Dmapred.input.dir must be provided to specify input directory");
        Preconditions.checkArgument(outputOptional || outputOption == null || outputPath != null,
                "No output specified:  or -Dmapred.output.dir must be provided to specify output directory");
    }



}

//
//public enum VectorSimilarityMeasures {
//
//    SIMILARITY_COOCCURRENCE(Test.class);
//
//    private final Class<? extends VectorSimilarityMeasure> implementingClass;
//
//    VectorSimilarityMeasures(Class<? extends VectorSimilarityMeasure> impl) {
//        this.implementingClass = impl;
//    }
//
//    public String getClassname() {
//        return implementingClass.getName();
//    }
//
//    public static String list() {
//        return Arrays.toString(values());
//    }
//
//}

class CommandLineUtil {

    private CommandLineUtil() { }

    public static void printHelp(Group group) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setGroup(group);
        formatter.print();
    }

    /**
     * Print the options supported by {@code GenericOptionsParser}.
     * In addition to the options supported by the job, passed in as the
     * group parameter.
     *
     * @param group job-specific command-line options.
     */
    public static void printHelpWithGenericOptions(Group group) throws IOException {
        new GenericOptionsParser(new Configuration(), new org.apache.commons.cli.Options(), new String[0]);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, Charsets.UTF_8), true);
        HelpFormatter formatter = new HelpFormatter();
        formatter.setGroup(group);
        formatter.setPrintWriter(pw);
        formatter.setFooter("Specify HDFS directories while running on hadoop; else specify local file system directories");
        formatter.print();
    }

    public static void printHelpWithGenericOptions(Group group, OptionException oe) throws IOException {
        new GenericOptionsParser(new Configuration(), new org.apache.commons.cli.Options(), new String[0]);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, Charsets.UTF_8), true);
        HelpFormatter formatter = new HelpFormatter();
        formatter.setGroup(group);
        formatter.setPrintWriter(pw);
        formatter.setException(oe);
        formatter.print();
    }

}