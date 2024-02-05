package cloud.voiture.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageException;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;

import cloud.voiture.model.Photo;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import java.nio.file.Path;

import org.springframework.core.io.Resource;

@Service
public class ImageUploadService {

    public ImageUploadService() {
        initializeFirebaseApp();
    }

    private void initializeFirebaseApp() {
        try {

            if (FirebaseApp.getApps().isEmpty()) {
                Resource resource = new ClassPathResource("firebase.json");
                InputStream serviceAccount = resource.getInputStream();

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setStorageBucket("voiture-cloud-pictures.appspot.com")
                        .build();

                FirebaseApp.initializeApp(options);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File[] convertToFile(String[] base64, int iduser) throws Exception {
        File[] imageFiles = new File[base64.length];

        for (int i = 0; i < base64.length; i++) {
            String base64Image = base64[i];
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            String uniqueId = UUID.randomUUID().toString();

            String fileName = "user_" + iduser + "_image_" + uniqueId + ".jpg";

            File imageFile = new File(fileName);

            // System.out.println("filename "+imageFile.getName());

            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                fos.write(imageBytes);
            }
            imageFiles[i] = imageFile;
        }

        return imageFiles;
    }

    private List<String> uploadToFirebaseStorage(File[] imageFiles) throws Exception {
        try {
            Bucket bucket = StorageClient.getInstance().bucket();
            List<String> urls = new ArrayList<>();

            for (int i = 0; i < imageFiles.length; i++) {
                Path filePath = imageFiles[i].toPath();
                byte[] bytes = Files.readAllBytes(filePath);
                String objectName = imageFiles[i].getName();

                // Uploader le fichier vers Firebase Storage
                bucket.create(objectName, bytes);

                // Construire l'URL du fichier
                String fileUrl = "https://firebasestorage.googleapis.com/v0/b/" + bucket.getName() + "/o/" + objectName
                        + "?alt=media";
                urls.add(fileUrl);

            }

            return urls;
        } catch (Exception e) {
            throw e;
        } finally {
            for (int j = 0; j < imageFiles.length; j++) {
                if (imageFiles[j].exists()) {
                    imageFiles[j].delete();
                }
            }
        }

    }

    public List<Photo> getUrl(String[] base64, int iduser) throws Exception {
        if(base64 == null){
            return new ArrayList<>();
        }
        File[] toUpload = this.convertToFile(base64, iduser);

        List<String> urls = uploadToFirebaseStorage(toUpload);
        List<Photo> val = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            val.add(new Photo(urls.get(i)));
        }

        return val;
    }
}