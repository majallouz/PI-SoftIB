package tn.esprit.softib.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.softib.entity.Card;
import tn.esprit.softib.entity.CardRequest;
import tn.esprit.softib.repository.CardRepository;

@Service
public class CardServiceImpl implements ICard {
	@Autowired
	CardRepository cardRepository;

	@Override
	public List<Card> getAllCards() {
		// TODO Auto-generated method stub
		return cardRepository.findAll();
	}

	@Override
	public Card getCardById(long id) {
		// TODO Auto-generated method stub
		Optional<Card> value = this.cardRepository.findById(id);
		if(value.isPresent()) {
		return value.get();
		} else return null ;
	}

	@Override
	public Card addCard(Card card) {
		// TODO Auto-generated method stub
		return cardRepository.save(card);
	}

	@Override
	public void deleteCard(long id) {
		// TODO Auto-generated method stub
		cardRepository.deleteById(id);
		
	}

	@Override
	public Card updateCard(Card card) {
		// TODO Auto-generated method stub
		return cardRepository.save(card);
	}
	
}
