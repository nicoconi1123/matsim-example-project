/* *********************************************************************** *
 * project: org.matsim.*												   *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */
package org.matsim.project;

import ch.sbb.matsim.config.SwissRailRaptorConfigGroup;
import ch.sbb.matsim.routing.pt.raptor.SwissRailRaptorModule;

import com.google.common.collect.ImmutableSet;
import net.bhl.matsim.uam.config.UAMConfigGroup;
import net.bhl.matsim.uam.qsim.UAMQSimModule;
import net.bhl.matsim.uam.qsim.UAMSpeedModule;
import net.bhl.matsim.uam.run.UAMModule;

import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.contrib.drt.routing.DrtRoute;
import org.matsim.contrib.drt.routing.DrtRouteFactory;
import org.matsim.contrib.dvrp.run.DvrpConfigGroup;
import org.matsim.contrib.dvrp.run.DvrpModule;
import org.matsim.contrib.dvrp.run.DvrpQSimComponents;
import org.matsim.contrib.taxi.run.MultiModeTaxiConfigGroup;
import org.matsim.contrib.taxi.run.MultiModeTaxiModule;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.QSimConfigGroup;
import org.matsim.core.config.groups.ScoringConfigGroup;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.scenario.ScenarioUtils;

/**
 * @author nagel
 *
 */
public class RunMatsim{

	public static void main(String[] args) {

		Config config;
		config = ConfigUtils.loadConfig( "scenarios/uam-test-scenario/uam_config.xml",
				new SwissRailRaptorConfigGroup(),
				new UAMConfigGroup());

		config.controller().setOverwriteFileSetting( OverwriteFileSetting.deleteDirectoryIfExists );


		Scenario scenario = ScenarioUtils.loadScenario(config) ;


		Controler controller = new Controler(scenario);

//		controller.addOverridingModule(new DvrpModule());

		controller.addOverridingModule(new SwissRailRaptorModule());
		controller.addOverridingModule(new MultiModeTaxiModule());
//		controller.configureQSimComponents(DvrpQSimComponents.activateModes(TransportMode.taxi));


		controller.run();
	}

}
