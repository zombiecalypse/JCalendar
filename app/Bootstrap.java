import models.User;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job {
	public void doJob() {
		// Check if the database is empty
		if (User.count() == 0) {
			Logger.info("Loading initial-data.yml");
			Fixtures.loadModels("initial-data.yml");
		}
	}
}
