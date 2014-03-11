/**
 * TestCanonical2PNML is a class for converting an CanonicalProcessType
 *  object into a PnmlType object.
 * <p>
 *
 * @author Martin SInger, Niko Waldow
 * @version     %I%, %G%
 * @since 1.0
 */

package org.apromore.canoniser.pnml.internal;

import java.util.logging.Logger;

import org.apromore.anf.AnnotationsType;
import org.apromore.canoniser.pnml.internal.canonical2pnml.AddXorOperators;
import org.apromore.canoniser.pnml.internal.canonical2pnml.DataHandler;
import org.apromore.canoniser.pnml.internal.canonical2pnml.RemoveConnectorTasks;
import org.apromore.canoniser.pnml.internal.canonical2pnml.RemoveEvents;
import org.apromore.canoniser.pnml.internal.canonical2pnml.RemoveSplitJoins;
import org.apromore.canoniser.pnml.internal.canonical2pnml.RemoveState;
import org.apromore.canoniser.pnml.internal.canonical2pnml.TranslateAnnotations;
import org.apromore.canoniser.pnml.internal.canonical2pnml.TranslateHumanResources;
import org.apromore.canoniser.pnml.internal.canonical2pnml.TranslateNet;
import org.apromore.canoniser.pnml.internal.canonical2pnml.TranslateSubnet;
import org.apromore.canoniser.pnml.internal.canonical2pnml.UpdateSpecialOperators;
import org.apromore.cpf.CanonicalProcessType;
import org.apromore.cpf.CPFSchema;
import org.apromore.cpf.NetType;
import org.apromore.cpf.ResourceTypeType;
import org.apromore.pnml.PNMLSchema;
import org.apromore.pnml.PnmlType;
import org.apromore.pnml.TransitionType;

public class Canonical2PNML {

    static final private Logger LOGGER = Logger.getLogger(Canonical2PNML.class.getCanonicalName());

    DataHandler data = new DataHandler();
    RemoveConnectorTasks removeConnectorTasks = new RemoveConnectorTasks();
    RemoveEvents removeEvents = new RemoveEvents();
    RemoveState removeState = new RemoveState();
    RemoveSplitJoins removeSplitJoins = new RemoveSplitJoins();
    TranslateAnnotations ta = new TranslateAnnotations();
    TranslateNet tn = new TranslateNet();
    private long ids = 0;  //System.currentTimeMillis();

    public PnmlType getPNML() {
        return data.getPnml();
    }

    /*
    public Canonical2PNML(CanonicalProcessType cproc) {
        removeConnectorTasks.setValue(data, cproc);
        removeConnectorTasks.remove();
        cproc = removeConnectorTasks.getCanonicalProcess();
        decanonise(cproc, null);
        ta.setValue(data);
    }
    */

    public Canonical2PNML(CanonicalProcessType cproc,
                          AnnotationsType      annotations,
                          boolean              isCpfTaskPnmlTransition,
                          boolean              isCpfEdgePnmlPlace) {

        data.setCpfTaskPnmlTransition(isCpfTaskPnmlTransition);
        data.setCpfEdgePnmlPlace(isCpfEdgePnmlPlace);

        removeConnectorTasks.setValue(data, cproc);
        removeConnectorTasks.remove();
        cproc = removeConnectorTasks.getCanonicalProcess();
        decanonise(cproc, annotations);
        ta.setValue(data);
        if (annotations != null) {
            ta.mapNodeAnnotations(annotations);
        }
    }

    public Canonical2PNML(CanonicalProcessType cproc, AnnotationsType annotations, String filename) {
        for (ResourceTypeType res : cproc.getResourceType()) {
            data.put_resourcemap(String.valueOf(res.getId()), res);
        }
        data.setAnno(annotations);
        data.setFilename(filename);

        removeEvents.setValue(annotations, data, cproc);
        removeEvents.remove();
        cproc = removeEvents.getCanonicalProcess();
        if (annotations != null) {
            annotations = removeEvents.getAnnotations();
        }
        removeConnectorTasks.setValue(data, cproc);
        removeConnectorTasks.remove();
        cproc = removeConnectorTasks.getCanonicalProcess();
        removeState.setValue(data, cproc);
        removeState.remove();
        cproc = removeState.getCanonicalProcess();
        removeSplitJoins.setValue(annotations, data, cproc);
        removeSplitJoins.remove();
        cproc = removeSplitJoins.getCanonicalProcess();
        if (annotations != null) {
            annotations = removeSplitJoins.getAnnotations();
        }
        decanonise(cproc, annotations);
        ta.setValue(data);
        if (annotations != null) {
            ta.mapNodeAnnotations(annotations);
        }
        AddXorOperators ax = new AddXorOperators();
        ax.setValues(data, ids);
        ax.add(cproc);
        ids = ax.getIds();
        cproc = ax.getCanonicalProcess();
        UpdateSpecialOperators uso = new UpdateSpecialOperators();
        uso.setValues(data, ids);
        uso.add(cproc);
        ids = uso.getIds();
        cproc = uso.getCanonicalProcess();
        if (data.getSubnet() != null) {
            if (data.getSubnet().size() == 1) {
                TransitionType obj = data.getSubnet().get(0);
                TranslateSubnet ts = new TranslateSubnet();

                ts.setValue(data, obj.getId());
                data.getSubnet().remove(0);
                ts.addSubnet();
                data = ts.getdata();
            }

        }
    }

    /**
     * This main method to be reused by all the constructors for all cases.
     * <p/>
     *
     * @since 1.0
     */
    private void decanonise(CanonicalProcessType cproc, AnnotationsType annotations) {
        for (NetType net : cproc.getNet()) {
            tn.setValues(data, ids, annotations);
            tn.translateNet(net);
            ids = tn.getIds();
        }

        TranslateHumanResources thr = new TranslateHumanResources();
        thr.setValues(data, ids);
        thr.translate(cproc);
        ids = thr.getIds();

        data.getNet().setId("noID");
        data.getNet().setType("http://www.informatik.hu-berlin.de/top/pntd/ptNetb");
        data.getPnml().getNet().add(data.getNet());
    }

    public static void main(String[] arg) throws Exception {

        CanonicalProcessType cpf = CPFSchema.unmarshalCanonicalFormat(System.in, true).getValue();
        PNMLSchema.marshalPNMLFormat(System.out, (new Canonical2PNML(cpf, null, false, false)).getPNML(), false);
    }
}
