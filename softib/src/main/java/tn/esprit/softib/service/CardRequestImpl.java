package tn.esprit.softib.service;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.softib.entity.Card;
import tn.esprit.softib.entity.CardRequest;

import tn.esprit.softib.entity.ConfirmationMessage;

import tn.esprit.softib.entity.User;
import tn.esprit.softib.enums.CardType;

import tn.esprit.softib.enums.FormStatus;

import tn.esprit.softib.enums.Status;
import tn.esprit.softib.repository.CardRequestRepository;

@Slf4j
@Service
public class CardRequestImpl implements ICardRequest {
	
	@Autowired
	CardRequestRepository cardRequestRepository;
	@Autowired
	IUserService userService;
	@Autowired
	ICard icard;

	@Override
	public List<CardRequest> getAllCardRequests() {
		// TODO Auto-generated method stub
		return cardRequestRepository.findAll();
	}

	@Override
	public CardRequest getCardRequestById(long id) {
		// TODO Auto-generated method stub
		return cardRequestRepository.findById(id).orElse(null);
	}

	@Override
	public CardRequest getCardRequestByRib(String rib) {
		// TODO Auto-generated method stub
		return cardRequestRepository.findByRib(rib).get();
	}

	@Override
	public CardRequest addCardRequest(CardRequest cardRequest) {
		// TODO Auto-generated method stub
		return cardRequestRepository.save(cardRequest);
	}

	@Override
	public void deleteCardRequest(long id) {
		// TODO Auto-generated method stub
		cardRequestRepository.deleteById(id);
		
	}

	@Override
	public CardRequest updateCardRequest(CardRequest oldCardRequest) {
		// TODO Auto-generated method stub
		return cardRequestRepository.save(oldCardRequest);
	}

	@Override
	public ConfirmationMessage confirmCardRequest(long id) {
		// TODO Auto-generated method stub
		ConfirmationMessage confMsg = new ConfirmationMessage();
		CardRequest cardRequest = getCardRequestById(id);
		
		//User user = userService.getUserByEmail(cardRequest.getEmail());
		User user = userService.getUserByCin(cardRequest.getCin());
		
		if (user != null && !checkCardTypeWithSalary(cardRequest.getCardType() , user.getSalaireNet())) {
			confMsg.setStatus(Status.KO);
			confMsg.setMessage("You can't get this CARD !!");
			cardRequest.setFormStatus(FormStatus.REJECTED);
			cardRequestRepository.save(cardRequest);
			return confMsg;
		}else if (user != null && checkCardTypeWithSalary(cardRequest.getCardType() , user.getSalaireNet())) {
			mapFormToCard(cardRequest, user);
			confMsg.setMessage("You can get your card in 2 weeks !!");
			confMsg.setStatus(Status.OK);
			cardRequest.setFormStatus(FormStatus.CONFIRMED);
			cardRequestRepository.save(cardRequest);
			return confMsg;
		} else {
			confMsg.setStatus(Status.KO);
			confMsg.setMessage("problem encountred");
			return confMsg;
		}
		
	}
	private boolean checkCardTypeWithSalary(CardType cardType, float salary) {
		if(salary < 1000 && cardType.equals(CardType.PINK)) {
			return true;
		} else if(Float.valueOf(salary) < Float.valueOf(2500) && cardType.equals(CardType.GREEN)) {
			return true;
		} else if(Float.valueOf(salary) < Float.valueOf(4500) && cardType.equals(CardType.RED)) {
			return true;
		} else if (Float.valueOf(salary) >= Float.valueOf(4500) && cardType.equals(CardType.GOLDEN) ){
			return true;
		}
		else {
			return false;
		}
	}
	
	private Card mapFormToCard(CardRequest cardRequest, User user) {
		Card card = new Card();
		card.setRib(cardRequest.getRib());
		card.setCardType(cardRequest.getCardType());
		return icard.addCard(card);
		
	}

	@Override
	public CardRequest rejectCardRequest(long id) {
		// TODO Auto-generated method stub
		CardRequest cardRequest = getCardRequestById(id);
		cardRequest.setFormStatus(FormStatus.REJECTED);
		cardRequestRepository.save(cardRequest);
		return cardRequest;
	}

	@Override
	public void deleteAutoCard() {
		List<CardRequest> cardrequests =  getAllCardRequests();
		List<CardRequest> cardrequestsFiltred = cardrequests.stream().filter(e -> e.getFormStatus().equals(FormStatus.REJECTED)).collect(Collectors.toList());
		for (CardRequest item : cardrequestsFiltred) {
			deleteCardRequest(item.getId());
		}
		
		
	}



}
