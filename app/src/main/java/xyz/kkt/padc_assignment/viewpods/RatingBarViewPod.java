package xyz.kkt.padc_assignment.viewpods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.kkt.padc_assignment.R;
import xyz.kkt.padc_assignment.components.loveanim.StarAnimView;

/**
 * Created by aung on 7/13/17.
 */

public class RatingBarViewPod extends LinearLayout {

    @BindView(R.id.iv_star_one)
    StarAnimView ivLoveOne;

    @BindView(R.id.iv_star_two)
    StarAnimView ivLoveTwo;

    @BindView(R.id.iv_star_three)
    StarAnimView ivLoveThree;

    @BindView(R.id.iv_star_four)
    StarAnimView ivLoveFour;

    @BindView(R.id.iv_star_five)
    StarAnimView ivLoveFive;

    @BindView(R.id.iv_star_six)
    StarAnimView ivLoveSix;

    private int mAppRating;

    public RatingBarViewPod(Context context) {
        super(context);
    }

    public RatingBarViewPod(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RatingBarViewPod(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    @OnClick(R.id.iv_star_one)
    public void onTapLoveOne(View view) {
        if (!ivLoveOne.isChecked()) {
            ivLoveOne.onClick(ivLoveOne);
        }

        if (ivLoveTwo.isChecked()) {
            ivLoveTwo.onClick(ivLoveTwo);
        }

        if (ivLoveThree.isChecked()) {
            ivLoveThree.onClick(ivLoveThree);
        }

        if (ivLoveFour.isChecked()) {
            ivLoveFour.onClick(ivLoveFour);
        }

        if (ivLoveFive.isChecked()) {
            ivLoveFive.onClick(ivLoveFive);
        }

        if (ivLoveSix.isChecked()) {
            ivLoveSix.onClick(ivLoveSix);
        }

        mAppRating = 1;
    }

    @OnClick(R.id.iv_star_two)
    public void onTapLoveTwo(View view) {
        if (!ivLoveOne.isChecked()) {
            ivLoveOne.onClick(ivLoveOne);
        }

        ivLoveTwo.onClick(ivLoveTwo);

        if (ivLoveThree.isChecked()) {
            ivLoveThree.onClick(ivLoveThree);
        }

        if (ivLoveFour.isChecked()) {
            ivLoveFour.onClick(ivLoveFour);
        }

        if (ivLoveFive.isChecked()) {
            ivLoveFive.onClick(ivLoveFive);
        }

        if (ivLoveSix.isChecked()) {
            ivLoveSix.onClick(ivLoveSix);
        }

        mAppRating = 2;
    }

    @OnClick(R.id.iv_star_three)
    public void onTapLoveThree(View view) {
        if (!ivLoveOne.isChecked()) {
            ivLoveOne.onClick(ivLoveOne);
        }

        if (!ivLoveTwo.isChecked()) {
            ivLoveTwo.onClick(ivLoveTwo);
        }

        ivLoveThree.onClick(ivLoveThree);

        if (ivLoveFour.isChecked()) {
            ivLoveFour.onClick(ivLoveFive);
        }

        if (ivLoveFive.isChecked()) {
            ivLoveFive.onClick(ivLoveFive);
        }

        if (ivLoveSix.isChecked()) {
            ivLoveSix.onClick(ivLoveSix);
        }

        mAppRating = 3;
    }

    @OnClick(R.id.iv_star_four)
    public void onTapLoveFour(View view) {
        if (!ivLoveOne.isChecked()) {
            ivLoveOne.onClick(ivLoveOne);
        }

        if (!ivLoveTwo.isChecked()) {
            ivLoveTwo.onClick(ivLoveTwo);
        }

        if (!ivLoveThree.isChecked()) {
            ivLoveThree.onClick(ivLoveThree);
        }

        ivLoveFour.onClick(ivLoveFour);

        if (ivLoveFive.isChecked()) {
            ivLoveFive.onClick(ivLoveFive);
        }

        if (ivLoveSix.isChecked()) {
            ivLoveSix.onClick(ivLoveSix);
        }

        mAppRating = 4;
    }

    @OnClick(R.id.iv_star_five)
    public void onTapLoveFive(View view) {
        if (!ivLoveOne.isChecked()) {
            ivLoveOne.onClick(ivLoveOne);
        }

        if (!ivLoveTwo.isChecked()) {
            ivLoveTwo.onClick(ivLoveTwo);
        }

        if (!ivLoveThree.isChecked()) {
            ivLoveThree.onClick(ivLoveThree);
        }

        if (!ivLoveFour.isChecked()) {
            ivLoveFour.onClick(ivLoveFour);
        }

        ivLoveFive.onClick(ivLoveFive);

        if (ivLoveSix.isChecked()) {
            ivLoveSix.onClick(ivLoveSix);
        }

        mAppRating = 5;
    }

    @OnClick(R.id.iv_star_six)
    public void onTapLoveSix(View view) {
        if (!ivLoveOne.isChecked()) {
            ivLoveOne.onClick(ivLoveOne);
        }

        if (!ivLoveTwo.isChecked()) {
            ivLoveTwo.onClick(ivLoveTwo);
        }

        if (!ivLoveThree.isChecked()) {
            ivLoveThree.onClick(ivLoveThree);
        }

        if (!ivLoveFour.isChecked()) {
            ivLoveFour.onClick(ivLoveFour);
        }

        if (ivLoveFive.isChecked()) {
            ivLoveFive.onClick(ivLoveFive);
        }

        ivLoveSix.onClick(ivLoveSix);
        mAppRating = 5;
    }

}
