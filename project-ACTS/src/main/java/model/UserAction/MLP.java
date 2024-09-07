package model.UserAction;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.nn.weights.WeightInit;

import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MLP {

    private MultiLayerNetwork model;

    public void trainModel(List<DataSet> trainingData) {

    	MultiLayerConfiguration config = new NeuralNetConfiguration.Builder()
                .seed(123)  // 랜덤 시드 설정
                .weightInit(WeightInit.XAVIER)  // 가중치 초기화 방식 설정
                .updater(org.nd4j.linalg.learning.config.Nesterovs.builder().build()) // 옵티마이저 설정
                .list()
                .layer(0, new DenseLayer.Builder()
                        .nIn(3)  // 입력 노드 수 (좋아요, 총 조회 시간, 조회수)
                        .nOut(10)  // 첫 번째 은닉층 노드 수
                        .activation(Activation.RELU)  // 활성화 함수
                        .build())
                .layer(1, new DenseLayer.Builder()
                        .nIn(10)
                        .nOut(10)  // 두 번째 은닉층 노드 수
                        .activation(Activation.RELU)
                        .build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.XENT)  // 출력층 설정
                        .nIn(10)
                        .nOut(1)  // 출력 노드 수 (좋아할 확률)
                        .activation(Activation.SIGMOID)
                        .build())
                .build();

        model = new MultiLayerNetwork(config);
        model.init();
        model.setListeners(new ScoreIterationListener(10));  // 학습 진행 상황 출력

        CustomDataSetIterator iterator = new CustomDataSetIterator(trainingData);

        while (iterator.hasNext()) {
            DataSet batch = iterator.next();
            model.fit(batch);
        }
    }

    public double predict(double[] userFeatures) {
    	
//        INDArray input = Nd4j.create(userFeatures);
//        INDArray output = model.output(input);
        
        INDArray input = Nd4j.create(userFeatures).reshape(1, userFeatures.length);  
        INDArray output = model.output(input);

        return output.getDouble(0);
    }

    private static class CustomDataSetIterator implements Iterator<DataSet> {

        private final List<DataSet> data;
        private int currentIndex = 0;
        private final int batchSize;

        public CustomDataSetIterator(List<DataSet> data) {
            this.data = data;
            this.batchSize = 10;  // 배치 크기 설정
        }

        @Override
        public boolean hasNext() {
            return currentIndex < data.size();
        }

        @Override
        public DataSet next() {
            int endIndex = Math.min(currentIndex + batchSize, data.size());
            List<DataSet> batch = data.subList(currentIndex, endIndex);
            
            // 배치의 모든 데이터셋을 2차원으로 변환
            List<DataSet> reshapedBatch = new ArrayList<>();
            for (DataSet ds : batch) {
            	System.out.println("Original Features Shape: " + Arrays.toString(ds.getFeatures().shape()));
                System.out.println("Original Labels Shape: " + Arrays.toString(ds.getLabels().shape()));

                // 1차원 -> 2차원 변환
                INDArray features = ds.getFeatures().reshape(1, ds.getFeatures().length()); 
                INDArray labels = ds.getLabels().reshape(1, ds.getLabels().length());

                // 변환 후의 차원 출력
                System.out.println("Reshaped Features Shape: " + Arrays.toString(features.shape()));
                System.out.println("Reshaped Labels Shape: " + Arrays.toString(labels.shape()));

                reshapedBatch.add(new DataSet(features, labels));
            }
            
            
            currentIndex = endIndex;
            return DataSet.merge(reshapedBatch);  // 배치를 병합하여 반환
        }
    }

}
