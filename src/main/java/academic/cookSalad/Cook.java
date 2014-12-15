package academic.cookSalad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cook {
	private static final Logger logger = LoggerFactory.getLogger(Cook.class);
	Status cookIt(Ingredients in, Dish what) {
		logger.debug("v03");
		Status sta = new Status();
		sta.setLeftOverIngrediants(in);

		Ingredients curr = null;
		try {
			curr = (Ingredients) in.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			logger.error("Clone did not work do a manual copy to curr ingredients TODO");
		}
		Ingredients req = new Ingredients();

		/*
		 * Cesars: Russian: 2 carrot 2 carrots 4 ice burg 2 beans 1 chicken 2
		 * chicken 1 beans
		 */
		if (what == Dish.CEASERS) {
			req.setCarrots(2);
			req.setIceBergLettuce(4);
			req.setChickens(1);
			req.setBeans(1);
		} else {
			req.setCarrots(2);
			req.setBeans(2);
			req.setChickens(2);
			// req.setIceBergLettuceQty(0);//Not required
		}

		processCarrrots(curr, req, sta);
		if (sta.isSuccess()) {
			processBeans(curr, req, sta);
			if (sta.isSuccess()) {
				processChickens(curr, req, sta);
				if (sta.isSuccess()) {
					processIceburg(curr, req, sta);
					if (sta.isSuccess()) {
						sta.setLeftOverIngrediants(curr);
					}
				}

			}

		}

		return sta;
	}

	private void processChickens(Ingredients curr, Ingredients req, Status sta) {
		for (int i = 0; i < req.getChickens(); i++) {
			processOneChicken(curr, sta);
			if (!sta.isSuccess()) {
				if (curr.getBeans() > 1) {
					// could have put a loop if more than 2
					// don't need to check for success as we checked available
					// quantity already
					processOneBean(curr, sta);
					processOneBean(curr, sta);
				} else if (curr.getCarrots() > 1) {
					processOneCarrot(curr, sta);
					processOneCarrot(curr, sta);
				} else {
					sta.setSuccess(false);
					return;
				}

			}
		}

	}

	private void processCarrrots(Ingredients curr, Ingredients req, Status sta) {
		for (int i = 0; i < req.getCarrots(); i++) {
			processOneCarrot(curr, sta);
			if (!sta.isSuccess()) {
				// check if failed after every ingredient. Else some other
				// ingredient might
				// be there and we get wrong result in some cases.
				// 1 bean or 2 ice
				if (curr.getBeans() > 0) {
					processOneBean(curr, sta);
				} else if (curr.getIceBergLettuce() > 1) {
					processOneIceburg(curr, sta);
					processOneIceburg(curr, sta);
				}
				return;
			}
		}

	}

	private void processOneCarrot(Ingredients curr, Status sta) {
		sta.setSuccess(false);
		if (curr.getCarrots() > 0) {
			curr.setCarrots(curr.getCarrots() - 1);
			sta.setSuccess(true);
		} else {
			sta.setSuccess(false);
		}
	}

	private void processIceburg(Ingredients curr, Ingredients req, Status sta) {
		for (int i = 0; i < req.getIceBergLettuce(); i++) {
			processOneIceburg(curr, sta);
			if (!sta.isSuccess()) {
				return;
			}
		}
	}

	private void processOneIceburg(Ingredients curr, Status sta) {
		if (curr.getIceBergLettuce() > 0) {
			curr.setIceBergLettuce(curr.getIceBergLettuce() - 1);
			sta.setSuccess(true);
		} else {
			sta.setSuccess(false);
		}

	}

	private void processBeans(Ingredients curr, Ingredients req, Status sta) {
		for (int i = 0; i < req.getBeans(); i++) {
			processOneBean(curr, sta);
			if (!sta.isSuccess()) {
				if (curr.getIceBergLettuce() > 0) {
					processOneIceburg(curr, sta);
				} else {
					return;
				}
			}
		}

	}

	private void processOneBean(Ingredients curr, Status sta) {
		if (curr.getBeans() > 0) {
			curr.setBeans(curr.getBeans() - 1);
			sta.setSuccess(true);
		} else {
			sta.setSuccess(false);
		}
	}

	private void processOneChicken(Ingredients curr, Status sta) {
		if (curr.getChickens() > 0) {
			curr.setChickens(curr.getChickens() - 1);
			sta.setSuccess(true);
		} else {
			sta.setSuccess(false);
		}
	}

}
