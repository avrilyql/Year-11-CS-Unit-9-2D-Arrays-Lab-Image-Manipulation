package code;

import image.Pixel;
import image.APImage;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
    //what how
        APImage image = new APImage("cyberpunk2077.jpg");
        image.draw();

        reflectImage("cyberpunk2077.jpg");
        edgeDetection("cyberpunk2077.jpg", 20);
        grayScale("cyberpunk2077.jpg");
        blackAndWhite("cyberpunk2077.jpg");
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        return (pixel.getRed() + pixel.getBlue() + pixel.getGreen())/3;
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Pixel pixHere = image.getPixel(j, i);
                int avg = getAverageColour(pixHere);
                pixHere.setRed(avg);
                pixHere.setBlue(avg);
                pixHere.setGreen(avg);
            }
        }
        image.draw();
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); i++) {
                Pixel pixHere = image.getPixel(j, i);
                if (getAverageColour(pixHere)<128) {
                    pixHere.setRed(0);
                    pixHere.setBlue(0);
                    pixHere.setGreen(0);
                }

                else {
                    pixHere.setRed(255);
                    pixHere.setBlue(255);
                    pixHere.setGreen(255);
                }
            }
        }
        image.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage image = new APImage(pathToFile);
        APImage result = new APImage(image.getWidth(), image.getHeight());

        for (int i = 0; i < image.getHeight()-1; i++)
        {
            for (int j = 1; j < image.getWidth(); j++)
            {
                Pixel pixHere = result.getPixel(j,i);
                Pixel pixel = image.getPixel(j,i);
                int current = getAverageColour(pixel);
                Pixel leftPixel = image.getPixel(j-1,i);
                int left = getAverageColour(leftPixel);
                Pixel bottomPixel = image.getPixel(j,i+1);
                int bottom = getAverageColour(bottomPixel);
                if (Math.abs(current - left) > threshold || Math.abs(current - bottom) > threshold)
                {
                    pixHere.setRed(0);
                    pixHere.setGreen(0);
                    pixHere.setBlue(0);
                }
                else
                {
                    pixHere.setBlue(255);
                    pixHere.setGreen(255);
                    pixHere.setRed(255);
                }
            }
        }
        result.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        APImage result = new APImage(image.getWidth(), image.getHeight());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j <image.getHeight(); j++) {
                Pixel pixHere = image.getPixel(i,j);
                result.setPixel(image.getWidth()-1-i, j, pixHere);
            }
        }
        result.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        APImage result = new APImage(image.getHeight(), image.getWidth());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j <image.getHeight(); j++) {
                Pixel pixHere = image.getPixel(i,j);
                result.setPixel(image.getHeight()-j, image.getWidth()-i, pixHere);
            }
        }
        result.draw();
    }

}
