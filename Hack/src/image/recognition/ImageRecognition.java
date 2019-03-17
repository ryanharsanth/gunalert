package image.recognition;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.io.PrintStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;

public class ImageRecognition {
	public static void main(String[] args) throws Exception{
		
		detectLabels("/Users/Mao/Downloads/34035439-the-man-holding-a-gun.jpg");
	}
	public static ArrayList<String> detectLabels(String filePath) throws Exception, IOException {
		  List<AnnotateImageRequest> requests = new ArrayList<>();
		  ArrayList<String> list=new ArrayList<String>();
		  ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

		  Image img = Image.newBuilder().setContent(imgBytes).build();
		  Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
		  AnnotateImageRequest request =
		      AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
		  requests.add(request);

		  try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
		    BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
		    List<AnnotateImageResponse> responses = response.getResponsesList();
		    
		    for (AnnotateImageResponse res : responses) {
		      if (res.hasError()) {
		       
		        return list;
		      }
		      
		      // For full list of available annotations, see http://g.co/cloud/vision/docs
		      for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
		    	  list.add(annotation.getDescription());
		    	
		      }
		     
		      Iterator<String> it = list.iterator();
		  		while(it.hasNext()){
		  			System.out.println(it.next());
		  		}
		  			    }
		  }
		return list;
		}
}
