package com.charith.ordee.services;

import com.charith.ordee.beans.FoodItemBean;
import com.charith.ordee.frequentItem.AprioriFrequentItemsetGenerator;
import com.charith.ordee.frequentItem.FrequentItemsetData;
import com.charith.ordee.repository.FoodItemRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
@Service
public class RecomendationService {
    private FrequentItemsetData<String> data;
    @Autowired
    private FoodItemRepository foodItemRepository;
    public void mineRules() throws FileNotFoundException {
        AprioriFrequentItemsetGenerator<String> generator =
                new AprioriFrequentItemsetGenerator<>();

        List<Set<String>> itemsetList = new ArrayList<>();
        File file = new File("./transaction.txt");



        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String temp = sc.nextLine();
                System.out.println(temp);
                String[] splitText = StringUtils.split(temp," ");
                System.out.println(splitText);
                itemsetList.add(new HashSet<>(Arrays.asList(splitText)));
            }
        }

        data = generator.generate(itemsetList, 0.5);





    }

    public List getRecomendations(Set list){

        List result = data.getRecomendation(list);
        List recomendation = new ArrayList<FoodItemBean>();
        for (Object i: result
             ) {
            System.out.println(i);
            FoodItemBean foodItemBean =foodItemRepository.getByFoodItemID((String) i);
            recomendation.add(foodItemBean);
        }
        return recomendation;
    }

    public void writeData(List<String> list) throws IOException {


        File editableQueryMap = new File("./transaction.txt");
        if (!editableQueryMap.exists()) {
            File defaultQueryMap = new ClassPathResource("transaction.txt").getFile();

            try (OutputStream os = Files.newOutputStream(editableQueryMap.toPath())) {
                Files.copy(defaultQueryMap.toPath(), os);
            }
        }
        FileWriter fileWriter = new FileWriter(editableQueryMap,true);
        System.out.println(list);

        String transaction="";
        for (String s:list
        ) {
            transaction+=(s+" ");
        }
        transaction+="\n";
        System.out.println(transaction);
        fileWriter.append(transaction);
        fileWriter.close();
        System.out.println("Order Done "+ transaction);

    }
}
