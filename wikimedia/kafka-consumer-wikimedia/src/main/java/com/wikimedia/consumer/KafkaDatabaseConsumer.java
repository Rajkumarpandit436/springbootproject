package com.wikimedia.consumer;

import com.wikimedia.consumer.entity.Wikimedia;
import com.wikimedia.consumer.repository.WikimediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private WikimediaRepository wikimediaRepository;

    public KafkaDatabaseConsumer(WikimediaRepository wikimediaRepository) {
        this.wikimediaRepository = wikimediaRepository;
    }

    @KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroup")
    public void consume(String eventMessage){

        LOGGER.info(String.format("event message received => "+eventMessage));

        Wikimedia wikimedia = new Wikimedia();
        wikimedia.setWikievent(eventMessage);
        wikimediaRepository.save(wikimedia);
    }
}
