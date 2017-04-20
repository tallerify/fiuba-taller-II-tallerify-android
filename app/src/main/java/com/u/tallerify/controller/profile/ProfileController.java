package com.u.tallerify.controller.profile;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.coordinators.Coordinator;
import com.squareup.coordinators.CoordinatorProvider;
import com.u.tallerify.R;
import com.u.tallerify.controller.FlowController;
import com.u.tallerify.presenter.AbstractPresenterGraph;
import com.u.tallerify.presenter.Presenter;
import com.u.tallerify.presenter.profile.ProfileUserActivityPresenter;
import com.u.tallerify.presenter.profile.ProfileUserContactsPresenter;
import com.u.tallerify.presenter.profile.ProfileUserInfoPresenter;
import com.u.tallerify.utils.CoordinatorsInstaller;

/**
 * Created by saguilera on 3/30/17.
 */
public class ProfileController extends FlowController {

    private static final int KEY_ACTIVITIES = 0;
    private static final int KEY_CONTACTS = 1;
    private static final int KEY_INFO = 2;

    @NonNull
    @Override
    protected View onCreateView(@NonNull final LayoutInflater inflater, @NonNull final ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_profile, container, false);

        view.findViewById(R.id.controller_profile_info).setTag(KEY_INFO);
        view.findViewById(R.id.controller_profile_activity).setTag(KEY_ACTIVITIES);
        view.findViewById(R.id.controller_profile_contacts).setTag(KEY_CONTACTS);

        return view;
    }

    @Override
    protected void onAttach(@NonNull final View view) {
        super.onAttach(view);

        CoordinatorsInstaller.installBinder(
            (ViewGroup) view.findViewById(R.id.controller_profile_root),
            new CoordinatorProvider() {
                private Graph graph;

                @Nullable
                @Override
                public Coordinator provideCoordinator(final View view) {
                    if (graph == null) {
                        graph = new Graph();
                    }
                    return graph.present(view);
                }
        });
    }

    @Nullable
    @Override
    protected String title() {
        return getResources().getString(R.string.toolbar_profile);
    }

    private static class Graph extends AbstractPresenterGraph {

        Graph() {
            add(KEY_INFO, new ProfileUserInfoPresenter());
            add(KEY_ACTIVITIES, new ProfileUserActivityPresenter());
            add(KEY_CONTACTS, new ProfileUserContactsPresenter());
        }

        @Nullable
        @Override
        public Presenter<?> present(@NonNull final View view) {
            return get((Integer) view.getTag());
        }

        @Override
        public int size() {
            return 3;
        }

    }

}